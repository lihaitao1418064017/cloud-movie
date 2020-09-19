package org.hhy.xxl.crud.dao;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author LiuHao
 * @date 2020/9/13 22:40
 * @description 删除接口
*/
public interface DeleteDao <E, PK extends Serializable> extends Dao {
    /**
     * 根据主键删除数据,并返回被删除数据的数量
     *
     * @param pk 主键
     * @return 删除的数据数量, 理论上此返回值应该为0或者1.
     */
    int delete(PK pk);

    /**
     * Deletes the given ids.
     *
     * @param ids
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    void deleteById(Collection<PK> ids);


    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    <S extends E> void delete(Collection<S> entities);

    /**
     * Deletes a given esEntity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given esEntity is {@literal null}.
     */
    <S extends E> void delete(S entity);
}