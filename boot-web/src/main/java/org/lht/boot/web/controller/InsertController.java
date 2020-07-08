package org.lht.boot.web.controller;

import cn.hutool.http.HttpStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.lht.boot.web.api.param.R;
import org.lht.boot.web.service.InsertService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.lht.boot.web.api.param.R.ok;

/**
 * @author LiHaitao
 * @description InsertController:
 * @date 2020/1/15 13:47
 **/
public interface InsertController<E, PK, VO> {


    <S extends InsertService<E, PK>> S getService();

    /**
     * 添加
     *
     * @param data
     * @return
     */
    @ApiOperation("添加")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.HTTP_OK, message = "操作成功"),
            @ApiResponse(code = HttpStatus.HTTP_INTERNAL_ERROR, message = "服务器内部异常"),
            @ApiResponse(code = HttpStatus.HTTP_FORBIDDEN, message = "权限不足"),
            @ApiResponse(code = HttpStatus.HTTP_UNAUTHORIZED, message = "无权限")})
    @PostMapping
    @PreAuthorize("hasPermission('*','SAVE')")
    default R<PK> add(@RequestBody VO data) {
        return ok(getService().insert(voToEntity(data)));
    }

    /**
     * VO to Entity
     *
     * @param model
     * @return
     */
    E voToEntity(VO model);
}
