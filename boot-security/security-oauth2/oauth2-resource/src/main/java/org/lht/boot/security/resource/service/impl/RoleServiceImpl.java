package org.lht.boot.security.resource.service.impl;

import org.lht.boot.security.resource.dao.RoleDao;
import org.lht.boot.security.resource.entity.Role;
import org.lht.boot.security.resource.entity.UserRole;
import org.lht.boot.security.resource.service.RoleService;
import org.lht.boot.security.resource.service.UserRoleService;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.TermEnum;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description RoleServiceImpl:
 * @date 2020/3/19 14:50
 **/
@Service
public class RoleServiceImpl extends BaseMybatisCrudServiceImpl<Role, Integer, RoleDao> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;


    @Override
    public List<Role> selectSignsByUser(String userId) {
        List<UserRole> userRoles = this.userRoleService
                .select(QueryParam.build("user_id", userId));
        return this.select(QueryParam.build(
                "id"
                , TermEnum.in
                , userRoles.stream()
                        .map(UserRole::getRoleId)
                        .collect(Collectors.toSet())));
    }
}
