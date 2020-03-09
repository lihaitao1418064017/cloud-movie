package org.lht.boot.web.api.param.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.*;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.lht.boot.web.api.param.*;
import org.lht.boot.web.common.exception.NotSupportedException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description ParamEsUtil: es参数工具类
 * @date 2020/1/13 17:15
 **/
public class ParamEsUtil {

    /**
     * 取要查询的字段
     *
     * @param builder
     * @param includes
     * @param excludes
     * @return
     */
    public static Search.Builder buildFieldsSearchBuilder(Search.Builder builder, Set<String> includes, Set<String> excludes) {
        if (CollectionUtils.isNotEmpty(includes)) {
            includes.stream().filter(StringUtils::isNotEmpty).forEach(builder::addSourceIncludePattern);
        }
        if (CollectionUtils.isNotEmpty(excludes)) {
            excludes.stream().filter(StringUtils::isNotEmpty).forEach(builder::addSourceExcludePattern);
        }
        return builder;
    }

    /**
     * 构建分页 Search.Builder
     *
     * @param param
     * @return Search.Builder
     */
    public static Search.Builder buildPageSearchBuilder(Param param) {
        SearchSourceBuilder searchSourceBuilder = buildSearchSourceBuilder(param);
        buildSortSearchSourceBuilder(searchSourceBuilder, param);
        if (param instanceof QueryParam) {
            QueryParam queryParam = (QueryParam) param;
            if (queryParam.isPaging()) {
                buildPageableSearchBuilder(queryParam, searchSourceBuilder);
            }
        }
        Search.Builder builder = buildSearchBuilder(searchSourceBuilder);
        return buildFieldsSearchBuilder(builder, param.getIncludes(), param.getExcludes());
    }

    /**
     * 构建聚合查询的Search.Builder
     *
     * @param param
     * @return
     */
    public static Search.Builder buildSearchBuilder(AggregationParam param) {
        SearchSourceBuilder searchSourceBuilder = buildSearchSourceBuilder(param);
        buildAggregation(param, searchSourceBuilder);
        Search.Builder builder = buildSearchBuilder(searchSourceBuilder);
        return buildFieldsSearchBuilder(builder, param.getIncludes(), param.getExcludes());
    }

    /**
     * 构建Search.Builder,通过指定SearchSourceBuilder
     *
     * @param searchSourceBuilder
     * @return
     */
    public static Search.Builder buildSearchBuilder(SearchSourceBuilder searchSourceBuilder) {
        return new Search.Builder(searchSourceBuilder.toString());
    }

    /**
     * 构建分页条件 SearchSourceBuilder
     *
     * @param queryParam
     * @param searchSourceBuilder
     */
    public static void buildPageableSearchBuilder(QueryParam queryParam, SearchSourceBuilder searchSourceBuilder) {
        if (queryParam != null) {
            searchSourceBuilder
                    .from(queryParam.getPageNo() * queryParam.getPageSize())
                    .size(queryParam.getPageSize());
        }
    }


    /**
     * 构建排序，自定义字段值的Search.Build
     *
     * @param param
     * @return Search.Build
     */
    public static Search.Builder buildAllSearchBuilder(Param param) {
        SearchSourceBuilder searchSourceBuilder = buildSearchSourceBuilder(param);
        buildSizeSearchBuilder(param, searchSourceBuilder);
        buildSortSearchSourceBuilder(searchSourceBuilder, param);
        Search.Builder builder = buildSearchBuilder(searchSourceBuilder);
        return buildFieldsSearchBuilder(builder, param.getIncludes(), param.getExcludes());
    }

    /**
     * Es默认查询10条，这里设置查询的最大数，通过pageSize
     *
     * @param param
     * @param searchSourceBuilder
     */
    private static void buildSizeSearchBuilder(Param param, SearchSourceBuilder searchSourceBuilder) {
        if (param instanceof QueryParam) {
            QueryParam queryParam = (QueryParam) param;
            searchSourceBuilder.size(queryParam.getPageSize());
        }
    }

    /**
     * 只构建查询条件的Search.Builder
     *
     * @param param
     * @return Search.Builder
     */
    public static Search.Builder buildSearchBuilder(Param param) {
        SearchSourceBuilder searchSourceBuilder = buildSearchSourceBuilder(param);
        buildSizeSearchBuilder(param, searchSourceBuilder);
        return new Search.Builder(searchSourceBuilder.toString());
    }


    /**
     * 排序条件
     *
     * @param searchSourceBuilder
     * @param param
     */
    private static void buildSortSearchSourceBuilder(SearchSourceBuilder searchSourceBuilder, Param param) {
        if (param instanceof QueryParam) {
            QueryParam queryParam = (QueryParam) param;
            List<Sort> sorts = queryParam.getSorts();
            sorts.forEach(order -> {
                if (order != null) {
                    searchSourceBuilder.sort(order.getName(), SortEnum.ASC.equals(order.getOrder()) ? SortOrder.ASC : SortOrder.DESC);
                }
            });
        }
    }


    /**
     * where and or 条件
     *
     * @param param
     * @return
     */
    public static SearchSourceBuilder buildSearchSourceBuilder(Param param) {
        List<Term> terms = param.getTerms();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        terms.forEach(term -> {
            QueryBuilder queryBuilder = buildQueryBuilder(term);
            switch (term.getType()) {
                case and:
                    boolQueryBuilder.must(queryBuilder);
                    break;
                case or:
                    boolQueryBuilder.should(queryBuilder);
                    boolQueryBuilder.minimumShouldMatch(1);//
                    break;
                default:
                    throw new NotSupportedException("operator[%s] not supported in elasticsearch TermType and or or");
            }
        });
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        return searchSourceBuilder;
    }

    public static void buildAggregation(AggregationParam param, SearchSourceBuilder searchSourceBuilder) {
        if (CollectionUtils.isNotEmpty(param.getGroupBys())) {
            AggregationBuilder aggregationBuilder;
            AggregationBuilder termsBuilder = null;
            AggregationBuilder subTermsBuilder = null;
            for (AggregationBuilder thisTermsBuilder : param.getGroupBys()) {
                if (termsBuilder == null) {
                    termsBuilder = thisTermsBuilder;
                } else {
                    subTermsBuilder.subAggregation(thisTermsBuilder);
                }
                subTermsBuilder = thisTermsBuilder;
            }
            for (AggregationBuilder configField : param.getAggregations()) {
                subTermsBuilder.subAggregation(configField);
            }
            aggregationBuilder = termsBuilder;
            searchSourceBuilder.aggregation(aggregationBuilder);
        } else {
            //聚合为空则只按给定字段sum
            for (AggregationBuilder aggregation : param.getAggregations()) {
                searchSourceBuilder.aggregation(aggregation);
            }
        }
    }

    /**
     * 构建Term条件
     *
     * @param term
     * @return
     */
    public static QueryBuilder buildQueryBuilder(Term term) {
        String column = term.getColumn();
        Object value = term.getValue();
        QueryBuilder queryBuilder;
        switch (term.getTermType()) {
            case gte:
                queryBuilder = QueryBuilders.rangeQuery(column).gte(value);
                break;
            case gt:
                queryBuilder = QueryBuilders.rangeQuery(column).gt(value);
                break;
            case lte:
                queryBuilder = QueryBuilders.rangeQuery(column).lte(value);
                break;
            case lt:
                queryBuilder = QueryBuilders.rangeQuery(column).lt(value);
                break;
            case btw:
                queryBuilder =
                        QueryBuilders.rangeQuery(column)
                                .from(((List) value).get(0))
                                .to(((List) value).get(1));
                break;
            case nbtw:
                queryBuilder =
                        QueryBuilders.rangeQuery(column)
                                .from(((List) value).get(0))
                                .to(((List) value).get(1));
                break;
            case in:
                queryBuilder = QueryBuilders.termsQuery(column, (List<Object>) value);
                break;
            case nin:
                queryBuilder = QueryBuilders.boolQuery().mustNot(QueryBuilders.termsQuery(column, (List<Object>) value));
                break;
            case like:
                String likeValue = StringUtils.replaceAll(String.valueOf(value), "%", "*");
                if (!StringUtils.startsWith(likeValue, "*") && StringUtils.endsWith(likeValue, "*")) {
                    queryBuilder = QueryBuilders.prefixQuery(column, StringUtils.removeEnd(likeValue, "*"));
                } else {
                    queryBuilder = QueryBuilders
                            .wildcardQuery(
                                    column,
                                    StringUtils.contains(likeValue, "*") ? likeValue : "*" + likeValue + "*");
                }
                break;
            case nlike:
                String nlikeValue = StringUtils.replaceAll(String.valueOf(value), "%", "*");
                if (!StringUtils.startsWith(nlikeValue, "*") && StringUtils.endsWith(nlikeValue, "*")) {
                    queryBuilder = QueryBuilders
                            .boolQuery()
                            .mustNot(QueryBuilders.prefixQuery(column, StringUtils.removeEnd(nlikeValue, "*")));
                } else {
                    queryBuilder = QueryBuilders
                            .boolQuery()
                            .mustNot(QueryBuilders.wildcardQuery(
                                    column,
                                    StringUtils.contains(nlikeValue, "*") ? nlikeValue : "*" + nlikeValue + "*"));

                }
                break;
            case eq:
                queryBuilder = QueryBuilders.termQuery(column, value);
                break;
            case not:
                queryBuilder = QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(column, value));
                break;
            case isnull:
                queryBuilder = QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery(column));
                break;
            case notnull:
                queryBuilder = QueryBuilders.existsQuery(column);
                break;
            case empty:
                queryBuilder = QueryBuilders.termsQuery(column, "");
                break;
            case nempty:
                queryBuilder = QueryBuilders.boolQuery().mustNot(QueryBuilders.termsQuery(column, ""));
                break;
            default:
                throw new NotSupportedException(String.format("operator[%s] not supported in elasticsearch TermType.", column));

        }
        return queryBuilder;

    }


    /**
     * @Description:
     * @Author: Lihaitao
     * @Date: 2020/3/9 14:25
     * @UpdateUser:
     * @UpdateRemark:
     */
    public static List<JSONObject> buildAggregationResult(AggregationParam aggregationParam, SearchResult result) {
        MetricAggregation aggregations = result.getAggregations();
        List<String> groupBys = aggregationParam.getGroupBys().stream().map(AggregationBuilder::getName).collect(Collectors.toList());
        List<JSONObject> groupList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(groupBys)) {
            TermsAggregation terms = aggregations.getTermsAggregation(groupBys.get(0));
            buildGroupByData(groupList, terms, groupBys, 0, new JSONObject(), aggregationParam.getAggregations());
        } else {
            buildStatsData(groupList, new JSONObject(), aggregations, aggregationParam.getAggregations());
        }
        return groupList;
    }

    public static void buildGroupByData(List<JSONObject> groupList, TermsAggregation terms, List<String> groupBys, int index, JSONObject jsonObject, List<AggregationBuilder> aggregationFields) {
        for (TermsAggregation.Entry b : terms.getBuckets()) {
            int groupIndex = index;
            String groupValue = b.getKeyAsString();
            String groupBy = groupBys.get(index);
            jsonObject.put(groupBy, groupValue);
            jsonObject.put(groupBy + "Count", b.getCount());
            if (groupBys.size() == (groupIndex + 1)) {
                buildStatsData(groupList, jsonObject, b, aggregationFields);
            } else {
                buildGroupByData(groupList, b.getTermsAggregation(groupBys.get(++groupIndex)), groupBys, groupIndex, jsonObject, aggregationFields);
            }

        }
    }

    public static void buildStatsData(List<JSONObject> groupList, JSONObject jsonObject, MetricAggregation b, List<AggregationBuilder> aggregationFields) {
        JSONObject jsonData = (JSONObject) jsonObject.clone();
        aggregationFields.stream().forEach(aggregation -> {
            String as = aggregation.getName();
            switch (aggregation.getType()) {
                case ValueCountAggregation.TYPE:
                    jsonData.put(as, b.getValueCountAggregation(as).getValueCount());
                    break;
                case SumAggregation.TYPE:
                    jsonData.put(as, b.getSumAggregation(as).getSum());
                    break;
                case MinAggregation.TYPE:
                    jsonData.put(as, b.getMinAggregation(as).getMin());
                    break;
                case MaxAggregation.TYPE:
                    jsonData.put(as, b.getMaxAggregation(as).getMax());
                    break;
                case AvgAggregation.TYPE:
                    jsonData.put(as, b.getAvgAggregation(as).getAvg());
                    break;
                case StatsAggregation.TYPE:
                    StatsAggregation statsAggregation = b.getStatsAggregation(as);
                    jsonData.put(as + "Avg", statsAggregation.getAvg());
                    jsonData.put(as + "Count", statsAggregation.getCount());
                    jsonData.put(as + "Max", statsAggregation.getMax());
                    jsonData.put(as + "Min", statsAggregation.getMin());
                    jsonData.put(as + "Sum", statsAggregation.getSum());
                    break;
                case GeoHashGridAggregation.TYPE:
                    GeoHashGridAggregation geoHashGridAggregation = b.getGeohashGridAggregation(as);
                    break;
            }
        });
        groupList.add(jsonData);
    }


}
