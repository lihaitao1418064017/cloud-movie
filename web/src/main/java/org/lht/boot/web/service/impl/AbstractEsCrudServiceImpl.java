package org.lht.boot.web.service.impl;

import org.lht.boot.web.api.param.AggregationParam;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.dao.AbstractElasticSearchCrudDao;
import org.lht.boot.web.domain.entity.BaseCrudEntity;
import org.lht.boot.web.service.AbstractEsCrudService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Description: ElasticSearch实现类
 *
 * @Author lht
 * @Date 2020/1/11 4:08 PM
 **/
public class AbstractEsCrudServiceImpl<E extends BaseCrudEntity<PK>, PK extends Serializable, Dao extends AbstractElasticSearchCrudDao<E, PK>> implements AbstractEsCrudService<E, PK, Dao> {

    @Autowired
    protected Dao dao;

    @Override
    public int delete(PK pk) {
        return dao.delete(pk);
    }

    @Override
    public <Q extends Param> int delete(Q param) {
        return dao.delete(param);
    }

    @Override
    public PK insert(E data) {
        return dao.add(data);
    }

    @Override
    public E get(PK id) {
        return dao.findOne(id);
    }

    @Override
    public List<E> get(List<PK> id) {
        return dao.findAll(id);
    }

    @Override
    public List<E> getAll() {
        return dao.findAll();
    }

    @Override
    public long count() {
        return dao.findAll().size();
    }

    @Override
    public <Q extends Param> PagerResult<E> selectPager(Q param) {
        return dao.selectPage(param);
    }

    @Override
    public <Q extends Param> List<E> select(Q param) {
        return dao.select(param);
    }

    @Override
    public <Q extends Param> long count(Q param) {
        return dao.count(param);
    }

    @Override
    public <Q extends Param> E selectSingle(Q param) {
        return dao.selectSingle(param);
    }

    @Override
    public PK update(PK id, E data) {
        data.setId(id);
        return dao.update(data);
    }

    @Override
    public PK update(E data) {
        return dao.update(data);
    }

    @Override
    public PK upsert(E entity) {
        return dao.upsert(entity);
    }

    @Override
    public List<PK> upsert(Collection<E> entities) {
        return dao.upsert(entities);
    }

    @Override
    public PK patch(PK id, E data) {
        data.setId(id);
        return dao.patch(data);
    }

    @Override
    public PK patch(E data) {
        return dao.patch(data);
    }


    @Override
    public <T> List<T> select(AggregationParam aggregationParam, Class<T> tClass) {
        return dao.select(aggregationParam, tClass);
    }


}
