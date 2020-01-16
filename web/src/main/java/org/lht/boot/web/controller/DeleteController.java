package org.lht.boot.web.controller;

import org.apache.commons.lang3.Validate;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.api.param.R;
import org.lht.boot.web.api.param.util.ParamServletUtil;
import org.lht.boot.web.service.DeleteService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiHaitao
 * @description DeleteController:
 * @date 2020/1/14 19:52
 **/
public interface DeleteController<PK> {


    <S extends DeleteService<PK>> S getService();


    @DeleteMapping("/{pk}")
    @ResponseBody
    default R delete(@PathVariable PK pk) {
        int result = getService().delete(pk);
        Validate.notNull(result, "data not exist");
        return R.ok(result);
    }

    @DeleteMapping("/deleteByParam")
    @ResponseBody
    default <Q extends Param> R delete(@RequestBody Q param, HttpServletRequest request) {
        ParamServletUtil.paddingTerms(param, request);
        int result = getService().delete(param);
        return R.ok(result);
    }

    @DeleteMapping("/deleteByIds")
    @ResponseBody
    default R delete(@RequestParam List<PK> ids) {
        int result = getService().delete(ids);
        return R.ok(result);
    }


}
