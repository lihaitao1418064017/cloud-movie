package org.lht.boot.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpStatus;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import org.apache.commons.lang3.Validate;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.lang.util.R;
import org.lht.boot.web.api.param.util.ParamServletUtil;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.service.QueryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description QueryController:查询
 * @date 2020/1/14 18:38
 **/
public interface QueryController<E, PK, VO, Q extends Param> {

    <S extends QueryService<E, PK>> S getService();

    /**
     * 根据id查询
     *
     * @param pk
     * @return
     */
    @ApiOperation("根据id查询")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "Object", name = "pk", value = "信息id", required = true)
    })
    @GetMapping("/{pk}")
    @ResponseBody
    @PreAuthorize("hasPermission('*','QUERY')")
    @AccessLogger("根据id查询")
    default R<VO> selectById(@PathVariable PK pk) {
        E result = getService().get(pk);
        Validate.notNull(result, "data not exist");
        return R.ok(entityToVo(result));
    }

    /**
     * 条件查询
     *
     * @param param
     * @param request
     * @return
     */
    @ApiOperation("根据条件查询")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "Object", name = "request", value = "查询条件", required = true)
            , @ApiImplicitParam(paramType = "body", dataType = "Object", name = "param", value = "查询条件对象", required = true)

    })
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasPermission('*','QUERY')")
    @AccessLogger("条件查询")
    default R<List> select(Q param, HttpServletRequest request) {
        ParamServletUtil.paddingTerms(param, request);
        List<E> list = getService().select(param);
        if (CollectionUtil.isEmpty(list)) {
            return R.ok(Lists.newArrayList());
        }
        return R.ok(list.stream().map(this::entityToVo).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     *
     * @param param
     * @param request
     * @return
     */
    @ApiOperation("分页查询")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "Object", name = "request", value = "查询条件url", required = true)
            , @ApiImplicitParam(paramType = "body", dataType = "Object", name = "param", value = "查询条件对象", required = true)

    })
    @GetMapping("/page")
    @ResponseBody
    @PreAuthorize("hasPermission('*','QUERY')")
    @AccessLogger("分页查询")
    default R<PagerResult<VO>> selectPage(Q param, HttpServletRequest request) {
        ParamServletUtil.paddingTerms(param, request);
        PagerResult<E> pagerResult = getService().selectPager(param);
        if (CollectionUtil.isEmpty(pagerResult.getRecords())) {
            return R.ok(new PagerResult<VO>());
        }
        return R.ok(pagerResult.convertTo(this::entityToVo));
    }

    /**
     * entity to VO
     *
     * @param e
     * @return
     */
    VO entityToVo(E e);
}
