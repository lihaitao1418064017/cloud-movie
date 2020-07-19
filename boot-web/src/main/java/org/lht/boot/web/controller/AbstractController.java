package org.lht.boot.web.controller;

import org.lht.boot.lang.util.R;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.domain.entity.BaseCrudEntity;
import org.lht.boot.web.domain.vo.CrudVO;
import org.lht.boot.web.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LiHaitao
 * @description AbstractController:
 * @date 2020/1/15 14:18
 **/
public abstract class AbstractController<E, PK, VO extends CrudVO<E, PK>, S extends CrudService<E, PK>> extends AbstractCrudController<E, PK, VO, QueryParam> {

    @Autowired
    protected S service;

    @Override
    public S getService() {
        return service;
    }


}
