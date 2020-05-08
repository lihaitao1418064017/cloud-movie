package org.lht.boot.security.service;

import org.lht.boot.security.entity.Permission;
import org.lht.boot.security.entity.RolePermission;
import org.lht.boot.security.entity.UserRole;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.TermEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description CustomPermissionEvaluator: 权限校验
 * @date 2020/3/19 15:58
 **/
@Configuration
public class SecPermissionEvaluator implements PermissionEvaluator {


    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserInfoService userInfoService;


    @Override
    public boolean hasPermission(Authentication authentication, Object url, Object per) {
        // 获得loadUserByUsername()方法的结果
        User userDetail = (User) authentication.getPrincipal();
        org.lht.boot.security.entity.User user = userInfoService.selectSingle(QueryParam.build("username", userDetail.getUsername()));
        List<Permission> permissions = findPermission(user);
        for (Permission permission : permissions) {
            if (url.equals(permission.getUrl()) && per.equals(permission.getName())) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }


    private List<Permission> findPermission(org.lht.boot.security.entity.User user) {
        List<UserRole> userRoles = this.userRoleService.select(QueryParam.build("user_id", user.getId()));
        final List<Integer> rolePermissions = this.rolePermissionService
                .select(QueryParam.build("role_id"
                        , TermEnum.in
                        , userRoles
                                .stream()
                                .map(UserRole::getRoleId)
                                .collect(Collectors.toSet())))
                .stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
        return this.permissionService
                .select(QueryParam.build("id"
                        , TermEnum.in
                        , rolePermissions));
    }

}
