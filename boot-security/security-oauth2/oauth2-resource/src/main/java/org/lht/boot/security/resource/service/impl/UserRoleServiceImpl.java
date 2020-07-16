package org.lht.boot.security.resource.service.impl;

import org.lht.boot.security.resource.dao.UserRoleDao;
import org.lht.boot.security.resource.entity.UserRole;
import org.lht.boot.security.resource.service.UserRoleService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description UserRoleServiceImpl:用户角色关联
 * @date 2020/3/19 14:50
 **/
@Service
public class UserRoleServiceImpl extends BaseMybatisCrudServiceImpl<UserRole, Integer, UserRoleDao> implements UserRoleService {

    @Override
    public Integer saveOrUpdate(UserRole role) {
        return dao.saveOrUpdate(role.getUserId(), role.getRoleId());
    }
}
