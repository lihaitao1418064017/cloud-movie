package org.hhy.xxl.crud.dao;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author LiuHao
 * @date 2020/9/13 22:02
 * @description 更新接口
*/
public interface UpdateDao<E,PK> extends Dao {
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
}
