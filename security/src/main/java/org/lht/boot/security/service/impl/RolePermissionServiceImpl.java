package org.lht.boot.security.service.impl;

import org.lht.boot.security.dao.RolePermissionDao;
import org.lht.boot.security.entity.RolePermission;
import org.lht.boot.security.service.RolePermissionService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description UserServiceImpl:
 * @date 2020/3/19 14:50
 **/
@Service
public class RolePermissionServiceImpl extends BaseMybatisCrudServiceImpl<RolePermission, Integer, RolePermissionDao> implements RolePermissionService {

}
