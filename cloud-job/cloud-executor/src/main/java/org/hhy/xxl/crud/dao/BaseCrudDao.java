package org.hhy.xxl.crud.dao;

import org.hhy.xxl.job.executor.bean.BaseEntity;
import java.io.Serializable;


/**
 *
 * @author LiuHao
 * @date 2020/9/13 15:55
 * @description 基础数据访问层接口CRUD
*/
public interface BaseCrudDao<E extends BaseEntity<PK>,PK extends Serializable> extends
    InsertDao<E,PK>,
    DeleteDao<E,PK>,
    UpdateDao<E,PK>,
    QueryDao<E,PK> {


}
