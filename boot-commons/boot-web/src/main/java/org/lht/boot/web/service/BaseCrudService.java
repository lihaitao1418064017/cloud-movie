package org.lht.boot.web.service;

import org.lht.boot.web.domain.entity.BaseCrudEntity;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description BaseCrudService:基础service
 * @date 2020/1/8 15:36
 **/
public interface BaseCrudService<E extends BaseCrudEntity<PK>, PK extends Serializable> extends CrudService<E, PK> {

}
