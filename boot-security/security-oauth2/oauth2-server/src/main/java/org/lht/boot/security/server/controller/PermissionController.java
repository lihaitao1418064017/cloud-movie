package org.lht.boot.security.server.controller;

import io.swagger.annotations.Api;
import org.lht.boot.lang.util.R;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.security.resource.service.PermissionService;
import org.lht.boot.security.resource.vo.PermissionVO;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.controller.AbstractController;
import org.lht.boot.web.controller.AbstractTreeController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/25 8:07 PM
 **/
@RestController
@RequestMapping("/permission")
@Api(tags = "权限相关接口", description = "提供权限相关的 Rest API")
@AccessLogger("权限模块")
public class PermissionController extends AbstractTreeController<Permission, Integer, PermissionVO, PermissionService> {

    /**
     * 树结构
     * @param roleId
     * @return
     */
    @GetMapping("getByRole/{roleId}")
    public R getPermissionByRoleId(@PathVariable Integer roleId){
        return R.ok(service.selectTreeByRoleId(roleId));
    }



}
