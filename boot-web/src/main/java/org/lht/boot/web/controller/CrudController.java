package org.lht.boot.web.controller;

import org.lht.boot.web.api.param.Param;
import org.lht.boot.lang.util.R;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.service.CrudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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


    /**
     * 注入service
     *
     * @return
     */
    @Override
    CrudService<E, PK> getService();

    /**
     * 唯一性校验
     *
     * @param name
     * @param value
     * @return
     */
    @GetMapping("/checkUnique")
    default R checkUnique(@RequestParam String name, @RequestParam String value) {
        return R.ok(getService().checkUnique(name, value));
    }




}
