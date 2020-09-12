package org.lht.boot.web.controller;

import cn.hutool.http.HttpStatus;
import io.swagger.annotations.*;
import org.apache.commons.lang3.Validate;
import org.lht.boot.lang.util.R;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.service.UpdateService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description UpdateController:更新
 * @date 2020/1/14 20:11
 **/
public interface UpdateController<E, PK, VO> {


    <S extends UpdateService<E, PK>> S getService();

    /**
     * 更新
     *
     * @param vo
     * @return
     */
    @ApiOperation("更新")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Object", name = "vo", value = "vo对象", required = true)

    })
    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasPermission('*','UPDTE')")
    @AccessLogger("更新")
    default R update(@RequestBody VO vo) {
        PK pk = getService().update(voToEntity(vo));
        return R.ok(pk);
    }


    /**
     * 根据id部分更新
     *
     * @param id
     * @param data
     * @return
     */
    @ApiOperation("根据id部分更新")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "Object", name = "id", value = "信息id", required = true)
            , @ApiImplicitParam(paramType = "body", dataType = "Object", name = "data", value = "vo对象", required = true)

    })
    @PatchMapping(path = "/{id}")
    @PreAuthorize("hasPermission('*','UPDTE')")
    @AccessLogger("根据id部分更新")
    default R<PK> patchByPrimaryKey(@PathVariable PK id, @RequestBody(required = false) VO data) {
        Validate.notNull(data, "patch data cannot be null.");
        return R.ok(getService().patch(id, voToEntity(data)));
    }

    /**
     * 保存或更新
     *
     * @param data
     * @return
     */
    @ApiOperation("添加或更新")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Object", name = "data", value = "vo对象", required = true)

    })
    @PutMapping
    @PreAuthorize("hasPermission('*','UPDTE')")
    @AccessLogger("添加或更新")
    default R<PK> saveOrUpdate(@RequestBody VO data) {
        return R.ok(getService().upsert(voToEntity(data)));
    }

    /**
     * vo to entity
     *
     * @param vo
     * @return E
     */
    E voToEntity(VO vo);

    /**
     * vos to entities
     *
     * @param vos
     * @return List<E>
     */
    default List<E> vosToEntities(Collection<VO> vos) {
        return vos.stream().map(this::voToEntity).collect(Collectors.toList());
    }

    /**
     * 添加唯一性校验
     *
     * @param name
     * @param value
     * @return
     */
    @GetMapping("edit/checkUnique")
    default R addCheckUnique(@RequestParam PK id, @RequestParam String name, @RequestParam String value) {
        return R.ok(getService().editCheckUnique(id, name, value));
    }

}
