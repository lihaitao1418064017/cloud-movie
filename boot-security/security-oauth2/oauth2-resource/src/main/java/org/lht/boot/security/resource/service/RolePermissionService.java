package org.lht.boot.security.resource.service;

import org.lht.boot.security.resource.entity.RolePermission;
import org.lht.boot.web.service.BaseCrudService;

import java.util.List;

/**
 * @author LiHaitao
 * @description RolePermissionService:角色和权限资源关联
 * @date 2020/3/19 14:48
 **/
public interface RolePermissionService extends BaseCrudService<RolePermission, Integer> {

    /**
     * 更新或添加，根据permission_id和role_id联合唯一索引
     *
     * @param rolePermissions
     * @return
     */
    Integer saveOrUpdate(List<RolePermission> rolePermissions);
}
