package org.lht.boot.web.dao;

import org.lht.boot.web.api.param.UpdateParam;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author LiHaitao
 * @description UpdateDao:更新操作
 * @date 2020/1/2 15:36
 **/
public interface UpdateDao<E, PK> extends Dao {

    /**
     * 全量字段更新
     *
     * @param e
     * @return
     */
    PK update(E e);

    /**
     * 全量字段更新(批量)
     *
     * @param entities
     * @return
     */
    List<PK> update(Collection<E> entities);

    /**
     * 更新插入
     *
     * @param e
     * @return
     */
    PK upsert(E e);

    /**
     * 批量更新插入
     *
     * @param entities
     * @return 主键集合
     */
    List<PK> upsert(Collection<E> entities);

    /**
     * 部分字段更新
     *
     * @param e
     * @return
     */
    PK patch(E e);


    /**
     * 部分字段更新(批量)
     *
     * @param entities
     * @return
     */
    List<PK> patch(Collection<E> entities);


    /**
     * 根据条件更新
     *
     * @param updateParam 携带更新的数据和条件
     * @return 更新数量
     * @throws IOException
     */
    int patch(UpdateParam<E> updateParam) throws IOException;
}
