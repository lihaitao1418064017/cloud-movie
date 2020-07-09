package org.lht.boot.web.service.impl;

import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.lht.boot.web.dao.CrudTreeDao;
import org.lht.boot.web.domain.entity.BaseCrudEntity;
import org.lht.boot.web.domain.entity.TreeEntity;
import org.lht.boot.web.service.CrudTreeService;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/9 9:56
 **/
public class CrudTreeServiceImpl<E extends TreeEntity, PK extends Serializable, Dao extends CrudTreeDao> extends BaseMybatisCrudServiceImpl<BaseCrudEntity<Serializable>, Serializable, BaseMybatisPlusDao<BaseCrudEntity<Serializable>>> implements CrudTreeService<E, PK> {


    @Override
    public List<E> selectAllChildNode(QueryParam queryParam, PK... pk) {
        return null;
    }

    @Override
    public List<E> selectChildNode(PK pk) {
        return null;
    }

    @Override
    public List<E> selectAllChildNode(PK pk, QueryParam queryParam) {
        return null;
    }

    @Override
    public List<E> convertToTree(List<E> entities) {
        return null;
    }

    @Override
    public PagerResult<E> selectAllChildNodePager(PK pk, QueryParam queryParam) {
        return null;
    }

    @Override
    public boolean isParentPresent(E e) {
        return false;
    }

    @Override
    public List<E> selectAsTree(PK pk, QueryParam queryParam) {
        return null;
    }

    @Override
    public List<E> selectAsTree(QueryParam queryParam) {
        return null;
    }

    @Override
    public E traceToTop(PK pk, QueryParam queryParam) {
        return null;
    }
}
