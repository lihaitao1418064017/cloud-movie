package org.lht.boot.security.service.impl;

import org.lht.boot.security.dao.PermissionDao;
import org.lht.boot.security.entity.Permission;
import org.lht.boot.security.service.PermissionService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description UserServiceImpl:
 * @date 2020/3/19 14:50
 **/
@Service
public class PermissionServiceImpl extends BaseMybatisCrudServiceImpl<Permission, Integer, PermissionDao> implements PermissionService {

}
