package org.lht.boot.web.dao;

import java.util.Collection;
import java.util.List;

/**
 * @author LiHaitao
 * @description InsertDao:插入Dao
 * @date 2020/1/2 15:35
 **/
public interface InsertDao<E, PK> extends Dao {

    PK add(E e);

    <S extends E> List<PK> add(Collection<S> entities);
}
