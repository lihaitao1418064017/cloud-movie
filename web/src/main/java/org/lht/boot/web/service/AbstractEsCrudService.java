package org.lht.boot.web.service;

import org.lht.boot.web.dao.AbstractElasticSearchCrudDao;
import org.lht.boot.web.domain.entity.CrudEntity;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description AbstractEsCrudService: ElasticSearch接口
 * @date 2020/1/8 15:37
 **/
public interface AbstractEsCrudService<E extends CrudEntity<PK>, PK extends Serializable, Dao extends AbstractElasticSearchCrudDao<E, PK>> extends CrudService<E, PK> {
}
