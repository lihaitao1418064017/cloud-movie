package org.hhy.xxl.crud.dao;

import org.hhy.xxl.job.executor.bean.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author LiuHao
 * @date 2020/9/13 23:03
 * @description ES CRUD 操作
*/
public class ElasticSearchCurdDao<E extends BaseEntity<PK>, PK extends Serializable> implements BaseCrudDao<E,PK> {

    @Override
    public int delete(PK pk) {
        return 0;
    }

    @Override
    public void deleteById(Collection<PK> ids) {

    }

    @Override
    public <S extends E> void delete(Collection<S> entities) {

    }

    @Override
    public <S extends E> void delete(S entity) {

    }

    @Override
    public PK add(E e) {
        return null;
    }

    @Override
    public <S extends E> List<PK> add(Collection<S> entities) {
        return null;
    }

    @Override
    public E findOne(PK pk) {
        return null;
    }

    @Override
    public List<E> findAll(Collection<PK> ids) {
        return null;
    }

    @Override
    public List<E> findAll() {
        return null;
    }

    @Override
    public PK update(E e) {
        return null;
    }

    @Override
    public List<PK> update(Collection<E> entities) {
        return null;
    }

    @Override
    public PK upsert(E e) {
        return null;
    }

    @Override
    public List<PK> upsert(Collection<E> entities) {
        return null;
    }

    @Override
    public PK patch(E e) {
        return null;
    }

    @Override
    public List<PK> patch(Collection<E> entities) {
        return null;
    }
}
