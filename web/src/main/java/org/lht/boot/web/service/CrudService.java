package org.lht.boot.web.service;

import cn.hutool.core.collection.CollectionUtil;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;

/**
 * @author LiHaitao
 * @description CrudService:增删改查Service
 * @date 2020/1/8 15:38
 **/
public interface CrudService<E, PK> extends
        InsertService<E, PK>
        , DeleteService<PK>
        , QueryService<E, PK>
        , UpdateService<E, PK> {

    default boolean checkUnique(String name, Object value) {
        QueryParam queryParam = new QueryParam();
        queryParam.addTerm(Term.build(name, value));
        return CollectionUtil.isNotEmpty(select(queryParam));
    }

}
