package org.lht.boot.security.resource.service;

import org.lht.boot.security.resource.entity.Role;
import org.lht.boot.web.service.BaseCrudService;

import java.util.List;

/**
 * @author LiHaitao
 * @description RoleService:角色
 * @date 2020/3/19 14:48
 **/
public interface RoleService extends BaseCrudService<Role, Integer> {

    /**
     * 获取用户所有角色标识
     *
     * @param userId
     * @return
     */
    List<Role> select(Integer userId);
}
