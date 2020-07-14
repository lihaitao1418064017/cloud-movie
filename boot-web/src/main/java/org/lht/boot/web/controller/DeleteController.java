package org.lht.boot.web.controller;

import cn.hutool.http.HttpStatus;
import io.swagger.annotations.*;
import org.apache.commons.lang3.Validate;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.api.param.R;
import org.lht.boot.web.api.param.util.ParamServletUtil;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.service.DeleteService;
import org.springframework.security.access.prepost.PreAuthorize;
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


    /**
     * 根据id删除
     *
     * @param pk
     * @return
     */
    @ApiOperation("根据id删除")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "Object", name = "pk", value = "信息id", required = true)
    })
    @DeleteMapping("/{pk}")
    @ResponseBody
    @PreAuthorize("hasPermission('*','DELETE')")
    @AccessLogger("根据id删除")
    default R delete(@PathVariable PK pk) {
        int result = getService().delete(pk);
        Validate.notNull(result, "data not exist");
        return R.ok(result);
    }

    /**
     * 条件删除
     *
     * @param param
     * @param request
     * @param <Q>
     * @return
     */
    @ApiOperation("根据条件删除")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Object", name = "param", value = "条件对象", required = true)
            , @ApiImplicitParam(paramType = "path", dataType = "Object", name = "request", value = "条件urk", required = true)

    })
    @DeleteMapping("/deleteByParam")
    @ResponseBody
    @AccessLogger("根据条件删除")
    default <Q extends Param> R delete(@RequestBody Q param, HttpServletRequest request) {
        ParamServletUtil.paddingTerms(param, request);
        int result = getService().delete(param);
        return R.ok(result);
    }

    /**
     * 根据id批量删除
     *
     * @param ids
     * @return
     */
    @ApiOperation("根据id批量删除")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Collection", name = "ids", value = "信息id集合", required = true)
    })
    @DeleteMapping("/deleteByIds")
    @ResponseBody
    @AccessLogger("根据id批量删除")
    default R delete(@RequestParam List<PK> ids) {
        int result = getService().delete(ids);
        return R.ok(result);
    }


}
