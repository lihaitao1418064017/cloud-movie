package org.lht.boot.security.resource.service.impl;

import org.lht.boot.security.resource.dao.RoleDao;
import org.lht.boot.security.resource.entity.Role;
import org.lht.boot.security.resource.service.RoleService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description UserServiceImpl:
 * @date 2020/3/19 14:50
 **/
@Service
public class RoleServiceImpl extends BaseMybatisCrudServiceImpl<Role, Integer, RoleDao> implements RoleService {

}
