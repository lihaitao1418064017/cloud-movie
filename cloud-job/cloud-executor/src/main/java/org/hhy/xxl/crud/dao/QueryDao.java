package org.hhy.xxl.crud.dao;

import org.hhy.xxl.job.executor.bean.BaseEntity;

import java.util.Collection;
import java.util.List;

public interface QueryDao <E extends BaseEntity, PK> extends Dao {


    /**
     * 根据id查询
     *
     * @param pk
     * @return
     */
    E findOne(PK pk);

    /**
     * 根据id查询
     *
     * @param ids
     * @return
     */
    List<E> findAll(Collection<PK> ids);


    /**
     * 查询全部
     *
     * @return
     */
    List<E> findAll();

}

