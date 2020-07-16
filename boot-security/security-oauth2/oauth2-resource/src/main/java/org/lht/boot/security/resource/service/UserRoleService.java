package org.lht.boot.security.resource.service;

import org.lht.boot.security.resource.entity.UserRole;
import org.lht.boot.web.service.BaseCrudService;

/**
 * @author LiHaitao
 * @description UserRoleService:用户角色关联
 * @date 2020/3/19 14:48
 **/
public interface UserRoleService extends BaseCrudService<UserRole, Integer> {

    /**
     * 更新或添加，根据user_id和role_id联合唯一索引
     *
     * @param role
     * @return
     */
    Integer saveOrUpdate(UserRole role);
}
