package org.lht.boot.web.controller;

import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.service.CrudService;

/**
 * @author LiHaitao
 * @description CrudController:
 * @date 2020/1/14 18:36
 **/
public interface CrudController<E, PK, VO, Q extends Param> extends
        QueryController<E, PK, VO, Q>
        , InsertController<E, PK, VO>
        , DeleteController<PK>
        , UpdateController<E, PK, VO> {


    @Override
    CrudService<E, PK> getService();

}
