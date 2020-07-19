package org.lht.boot.security.resource.service.impl;

import org.lht.boot.security.resource.dao.PermissionDao;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.security.resource.service.PermissionService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.lht.boot.web.service.impl.CrudTreeServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiHaitao
 * @description PermissionServiceImpl:权限资源信息
 * @date 2020/3/19 14:50
 **/
@Service
public class PermissionServiceImpl extends CrudTreeServiceImpl<Permission, Integer, PermissionDao> implements PermissionService {

    @Override
    public List<Permission> select(Integer userId) {
        return dao.selectByUserId(userId);
    }

    @Override
    public List<Permission> selectTreeByRoleId(Integer roleId) {
        return convertToTree(dao.selectByRoleId(roleId));
    }
}
