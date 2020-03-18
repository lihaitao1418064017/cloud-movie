package org.lht.boot.web.service;

import org.lht.boot.web.api.param.AggregationParam;
import org.lht.boot.web.dao.AbstractElasticSearchCrudDao;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiHaitao
 * @description AbstractEsCrudService: ElasticSearch接口
 * @date 2020/1/8 15:37
 **/
public interface AbstractEsCrudService<E extends BaseCrudEntity<PK>, PK extends Serializable, Dao extends AbstractElasticSearchCrudDao<E, PK>> extends CrudService<E, PK> {
    /**
     * 聚合查询
     *
     * @param aggregationParam
     * @param tClass
     * @param <T>
     * @return
     */
    <T> List<T> select(AggregationParam aggregationParam, Class<T> tClass);
}
