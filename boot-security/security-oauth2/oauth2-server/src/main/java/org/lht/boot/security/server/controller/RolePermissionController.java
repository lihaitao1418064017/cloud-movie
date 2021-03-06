package org.lht.boot.security.server.controller;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.Api;
import org.lht.boot.lang.util.R;
import org.lht.boot.security.resource.entity.RolePermission;
import org.lht.boot.security.resource.service.RolePermissionService;
import org.lht.boot.security.resource.vo.RolePermissionVO;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:角色和权限关联接口
 *
 * @Author lht
 * @Date 2020/3/25 8:07 PM
 **/
@RestController
@RequestMapping("/role_permission")
@Api(tags = "角色和权限相关接口", description = "提供角色和权限相关的 Rest API")
@AccessLogger("角色权限关联模块")
public class RolePermissionController extends AbstractController<RolePermission, Integer, RolePermissionVO, RolePermissionService> {

    /**
     * 更新或者添加
     *
     * @param vos 角色和权限id的vo
     * @return 保存结果
     */
    @PutMapping("/saveOrUpdate")
    public R<Integer> saveOrUpdate(@RequestBody List<RolePermissionVO> vos) {
        if (CollectionUtil.isEmpty(vos)) {
            return R.ok();
        }
        return R.ok(service.saveOrUpdate(vosToEntities(vos)));
    }
}
