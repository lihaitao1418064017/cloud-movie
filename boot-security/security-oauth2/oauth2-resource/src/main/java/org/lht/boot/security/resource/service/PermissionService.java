package org.lht.boot.security.resource.service;

import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.web.service.BaseCrudService;

import java.util.List;

/**
 * @author LiHaitao
 * @description PermissionService:权限资源
 * @date 2020/3/19 14:48
 **/
public interface PermissionService extends BaseCrudService<Permission, Integer> {

    /**
     * 根据用户名获取用户权限资源信息
     *
     * @param userId
     * @return
     */
    List<Permission> select(Integer userId);
}
