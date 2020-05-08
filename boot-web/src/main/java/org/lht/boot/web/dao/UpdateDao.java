package org.lht.boot.web.dao;

import com.alibaba.fastjson.JSONObject;
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

    PK upsert(E e);

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


    int patch(UpdateParam<JSONObject> updateParam) throws IOException;
}
