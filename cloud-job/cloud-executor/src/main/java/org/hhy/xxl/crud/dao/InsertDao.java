package org.hhy.xxl.crud.dao;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author LiuHao
 * @date 2020/9/13 21:48
 * @description 添加接口
*/
public interface InsertDao<E, PK> extends Dao {

    /**
     *
     * @param e
     * @return
     */
    PK add(E e);

    /**
     *
     * @param entities
     * @param <S>
     * @return
     */
    <S extends E> List<PK> add(Collection<S> entities);

}
