package org.lht.boot.security.resource.service.impl;

import org.lht.boot.security.resource.dao.RolePermissionDao;
import org.lht.boot.security.resource.entity.RolePermission;
import org.lht.boot.security.resource.service.RolePermissionService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description RolePermissionServiceImpl:
 * @date 2020/3/19 14:50
 **/
@Service
public class RolePermissionServiceImpl extends BaseMybatisCrudServiceImpl<RolePermission, Integer, RolePermissionDao> implements RolePermissionService {


    @Override
    public Integer saveOrUpdate(RolePermission rolePermission) {
        return dao.saveOrUpdate(rolePermission.getPermissionId(), rolePermission.getRoleId());
    }
}
