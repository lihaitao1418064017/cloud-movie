package org.lht.boot.web.dao;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.lht.boot.lang.util.BeanUtils;
import org.lht.boot.lang.util.ClassUtil;
import org.lht.boot.web.api.param.AggregationParam;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.util.ParamEsUtil;
import org.lht.boot.web.common.exception.JestException;
import org.lht.boot.web.common.util.JestUtil;
import org.lht.boot.web.domain.entity.BaseCrudEntity;
import org.lht.boot.web.domain.entity.EsEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author LiHaitao
 * @description ElasticSearchCrudDao:ElasticSearch实现类
 * @date 2020/1/2 18:23
 **/
@Slf4j
public class ElasticSearchCrudDao<E extends BaseCrudEntity<PK>, PK extends Serializable> implements EsCrudDao<E, PK> {

    @Autowired
    private JestClient jestClient;
    protected EsEntity esEntity;
    protected Integer searchMaxSize;
    /**
     * 获取E的Class对象
     */
    private Class<E> entityType;
    private boolean refresh = false;
    @Autowired
    private Gson gson;


    public ElasticSearchCrudDao() {
        //获取当前实体E上的的注解
        entityType = (Class<E>) ClassUtil.getGenericType(this.getClass(), 0);
        this.esEntity = ClassUtil.getAnnotation(this.entityType, EsEntity.class);
        this.searchMaxSize = 9999;
    }


    @Override
    public int delete(PK pk) {
        Delete delete = new Delete
                .Builder(String.valueOf(pk))
                .index(this.getAlias())
                .type(this.getType())
                .refresh(this.refresh)
                .build();
        JestResult execute = JestUtil.execute(jestClient, gson, delete);
        return execute.getResponseCode();
    }

    @Override
    public void deleteById(Collection<PK> ids) {
        Bulk.Builder builder = new Bulk.Builder();
        ids.forEach(id -> builder.addAction(new Delete.Builder(String.valueOf(id)).index(getAlias()).type(getType()).build()));
        Bulk bulk = builder.refresh(refresh).build();
        JestUtil.execute(jestClient, gson, bulk);
    }

    @Override
    public <S extends E> void delete(Collection<S> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }
        Bulk.Builder builder = new Bulk.Builder();
        entities.forEach(entity -> builder.addAction(new Delete.Builder(String.valueOf(entity.getId())).index(getAlias()).type(getType()).build()));
        Bulk bulk = builder.refresh(refresh).build();
        JestUtil.execute(jestClient, gson, bulk);
    }

    @Override
    public <S extends E> void delete(S entity) {
        Validate.notNull(entity.getId());
        this.delete(entity.getId());

    }

    @Override
    public <Q extends Param> int delete(Q param) {
        DeleteByQuery deleteByQuery = new io.searchbox.core.DeleteByQuery
                .Builder(ParamEsUtil.buildSearchSourceBuilder(param).toString())
                .addIndex(this.getAlias())
                .addType(this.getType())
                .setParameter("scroll_size", Integer.valueOf(5000))
                .refresh(this.refresh)
                .setParameter("slices", Integer.valueOf(5))
                .build();
        JestResult result = JestUtil.execute(jestClient, gson, deleteByQuery);
        return result.getJsonObject().get("deleted").getAsInt();
    }


    @Override
    public PK add(E e) {
        Index index = new io.searchbox.core.Index
                .Builder(e)
                .index(getIndex())
                .type(this.getType())
                .id(this.buildPk(e))
                .refresh(this.refresh)
                .build();
        DocumentResult result = JestUtil.execute(jestClient, gson, index);
        return (PK) result.getId();
    }

    private String getIndex() {
        return this.esEntity.index();
    }

    private String buildPk(E e) {
        String id = (String) e.getId();
        return StrUtil.isBlank(id) ? RandomUtil.simpleUUID() : id;
    }

    @Override
    public <S extends E> List<PK> add(Collection<S> entities) {
        Validate.notEmpty(entities);
        Bulk.Builder bulk = new Bulk.Builder();
        entities.forEach(e -> {
            Index index = new Index.Builder(e)
                    .index(getIndex())
                    .type(getType())
                    .id(buildPk(e))
                    .build();
            bulk.addAction(index);
        });
        BulkResult execute = JestUtil.execute(jestClient, gson, bulk.build());
        Set failedIds = (Set) execute
                .getFailedItems()
                .stream()
                .map((item) -> {
                    return item.id;
                })
                .collect(Collectors.toSet());
        return entities
                .stream()
                .map(E::getId)
                .filter((pk) -> {
                    return !failedIds.contains(pk);
                })
                .collect(Collectors.toList());
    }


    private Update build(E data) {
        String payload;
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(BeanUtils.objectToMap(data));
        try {
            payload = jsonBuilder()
                    .startObject()
                    .field("doc_as_upsert", true)
                    .field("doc", jsonObject)
                    .endObject()
                    .string();
        } catch (IOException e) {
            throw new JestException("commons.dao.es.json", e);
        }
        Update.Builder builder = new Update.Builder(payload)
                .index(this.getAlias())
                .type(this.getType())
                .id(buildPk(data))
                .refresh(refresh);
        return builder.build();
    }


    @Override
    public E findOne(PK pk) {
        Get get = new Get.Builder(getAlias(), (String) pk).type(getType()).build();
        DocumentResult execute = JestUtil.execute(jestClient, gson, get);
        return execute.getSourceAsObject(this.entityType);
    }

    @Override
    public List<E> findAll(Collection<PK> ids) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query(QueryBuilders.termsQuery("_id", ids.toArray()));
        Search.Builder builder = new Search
                .Builder(searchSourceBuilder.toString());
        builder.addIndex(getAlias()).addType(getType());
        SearchResult result = JestUtil.execute(jestClient, gson, builder.build());
        return result.getSourceAsObjectList(this.entityType, true);
    }

    @Override
    public List<E> findAll() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchAllQuery())
                .size(this.getSearchMaxSize());
        Search.Builder builder = new Search
                .Builder(searchSourceBuilder.toString());
        builder.addIndex(getAlias()).addType(getType());
        SearchResult result = JestUtil.execute(jestClient, gson, builder.build());
        return result.getSourceAsObjectList(this.entityType, true);
    }

    @Override
    public <Q extends Param> Long count(Q param) {
        SearchSourceBuilder searchSourceBuilder = ParamEsUtil.buildSearchSourceBuilder(param);
        Count count = new Count.Builder()
                .addIndex(getAlias())
                .addType(getType())
                .query(searchSourceBuilder.toString())
                .build();
        CountResult result = JestUtil.execute(jestClient, gson, count);
        return result.getCount() == null ? 0 : result.getCount().longValue();
    }


    @Override
    public <Q extends Param> PagerResult<E> selectPage(Q param) {
        if (param instanceof QueryParam) {
            QueryParam queryParam = (QueryParam) param;
            Search.Builder builder = ParamEsUtil.buildPageSearchBuilder(queryParam);
            builder.addIndex(getAlias()).addType(getType());
            SearchResult result = JestUtil.execute(jestClient, gson, builder.build());
            List<E> eList = result.getSourceAsObjectList(this.entityType, true);
            PagerResult page = new PagerResult();
            page.setRecords(eList);
            page.setTotal(result.getTotal());
            page.setSize(queryParam.getPageSize());
            page.setCurrent(queryParam.getPageNo());
            page.setTotalPages(page.getPages());
            return page;
        }
        return null;
    }

    @Override
    public <Q extends Param> List<E> select(Q param) {
        Search.Builder builder = ParamEsUtil.buildAllSearchBuilder(param);
        builder.addIndex(getAlias()).addType(getType());
        SearchResult result = JestUtil.execute(jestClient, gson, builder.build());
        return result.getSourceAsObjectList(this.entityType, true);
    }


    @Override
    public PK update(E e) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.should(QueryBuilders.termQuery("_id", e.getId()));
        try {
            Map map = BeanUtils.objectToMap(e, true);
            map.remove("id");
            String update = XContentFactory
                    .jsonBuilder()
                    .startObject()
                    .field("query", queryBuilder)
                    .startObject("script")
                    .field("inline", JestUtil.buildScript(map, true))
                    .endObject()
                    .endObject()
                    .string();
            UpdateByQuery.Builder builder = new UpdateByQuery
                    .Builder(update)
                    .refresh(refresh)
                    .addIndex(this.getAlias())
                    .addType(this.getType());
            JestUtil.execute(jestClient, gson, builder.build());
        } catch (IOException e1) {
            throw new JestException("updateByQuery error", e1);
        }
        return e.getId();
    }


    public PK update(String idOrCode, Map<String, Object> params, E entity, boolean refresh) {
        Script script = new Script(Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG, idOrCode, params);
        Update.Builder builder = new Update
                .Builder(JestUtil.buildScript(script))
                .index(getAlias())
                .type(getType())
                .id(buildPk(entity))
                .setParameter("retry_on_conflict", 3)
                .refresh(refresh);
        DocumentResult execute = JestUtil.execute(jestClient, gson, builder.build());
        return (PK) execute.getId();

    }


    @Override
    public List<PK> update(Collection<E> entities) {
        Bulk.Builder bulk = new Bulk.Builder();
        entities.forEach(e -> {
            bulk.addAction(build(e));
        });
        BulkResult execute = JestUtil.execute(jestClient, gson, bulk.build());
        Set failedIds = (Set) execute
                .getFailedItems()
                .stream()
                .map((item) -> {
                    return item.id;
                })
                .collect(Collectors.toSet());
        return entities
                .stream()
                .map(E::getId)
                .filter((pk) -> {
                    return !failedIds.contains(pk);
                })
                .collect(Collectors.toList());
    }

    @Override
    public PK upsert(E e) {
        Update build = this.build(e);
        DocumentResult documentResult = JestUtil.execute(jestClient, gson, build);
        return (PK) documentResult.getId();
    }


    @Override
    public List<PK> upsert(Collection<E> entities) {
        io.searchbox.core.Bulk.Builder builder = new io.searchbox.core.Bulk.Builder();
        entities.forEach((e) -> {
            builder.addAction(this.build(e));
        });
        Bulk bulk = builder
                .refresh(this.refresh)
                .build();
        BulkResult bulkResult = JestUtil.execute(jestClient, gson, bulk);
        return (List<PK>) bulkResult
                .getItems()
                .stream()
                .map((item) -> {
                    return (PK) item.id;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PK patch(E e) {
        Map map = BeanUtils.objectToMap(e, false);
        String idOrCode = JestUtil.buildScript(map, false);
        return update(idOrCode, Maps.newHashMap(), e, true);
    }

    @Override
    public List<PK> patch(Collection<E> entities) {
        entities.forEach(this::patch);
        return entities
                .stream()
                .map(E::getId)
                .collect(Collectors.toList());
    }


    private String getAlias() {
        return this.esEntity.alias() == null ? esEntity.index() : this.esEntity.alias();
    }

    public String getType() {
        return this.esEntity.type();
    }

    public void setRefresh(Boolean refresh) {
        this.refresh = refresh;
    }

    public Integer getSearchMaxSize() {
        return searchMaxSize;
    }

    public void setSearchMaxSize(Integer searchMaxSize) {
        this.searchMaxSize = searchMaxSize;
    }


    /**
     * 聚合查询
     *
     * @param aggregationParam
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> select(AggregationParam aggregationParam, Class<T> clazz) {
        Search.Builder builder = ParamEsUtil
                .buildSearchBuilder(aggregationParam)
                .addIndex(getAlias())
                .addType(getType());
        SearchResult result = JestUtil.execute(jestClient, gson, builder.build());
        List<JSONObject> jsonObjects = ParamEsUtil.buildAggregationResult(aggregationParam, result);
        return jsonObjects
                .stream()
                .map(jsonObject -> JSONObject.toJavaObject(jsonObject, clazz))
                .collect(Collectors.toList());
    }
}
