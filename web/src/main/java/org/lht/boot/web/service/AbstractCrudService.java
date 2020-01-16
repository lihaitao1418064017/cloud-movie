package org.lht.boot.web.service;

import org.lht.boot.web.domain.entity.CrudEntity;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description AbstractCrudService:
 * @date 2020/1/8 15:36
 **/
public interface AbstractCrudService<E extends CrudEntity<PK>, PK extends Serializable> extends CrudService<E, PK> {

}
