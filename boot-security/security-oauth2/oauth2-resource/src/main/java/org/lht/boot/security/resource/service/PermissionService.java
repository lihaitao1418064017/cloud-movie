package org.lht.boot.security.resource.service;

import org.apache.ibatis.annotations.Param;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.web.service.BaseCrudService;
import org.lht.boot.web.service.CrudTreeService;

import java.util.List;

/**
 * @author LiHaitao
 * @description PermissionService:权限资源
 * @date 2020/3/19 14:48
 **/
public interface PermissionService extends CrudTreeService<Permission, Integer> {

    /**
     * 根据用户名获取用户权限资源信息
     *
     * @param userId
     * @return
     */
    List<Permission> select(Integer userId);

    /**
     * 根据角色id获取权限资源信息
     * @param roleId
     * @return
     */
    List<Permission> selectTreeByRoleId(Integer roleId);

}
