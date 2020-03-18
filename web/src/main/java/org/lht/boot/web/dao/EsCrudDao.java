package org.lht.boot.web.dao;

import org.lht.boot.web.domain.entity.BaseCrudEntity;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description CrudDao:
 * @date 2020/1/2 15:32
 **/
public interface EsCrudDao<E extends BaseCrudEntity<PK>, PK extends Serializable> extends
        InsertDao<E, PK>,
        UpdateDao<E, PK>,
        DeleteDao<E, PK>,
        QueryDao<E, PK> {
}
