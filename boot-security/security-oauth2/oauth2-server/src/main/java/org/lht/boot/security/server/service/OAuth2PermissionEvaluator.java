package org.lht.boot.security.server.service;

import org.lht.boot.security.entity.AuthPermission;
import org.lht.boot.security.entity.AuthUserDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Set;

/**
 * @author LiHaitao
 * @description CustomPermissionEvaluator: 权限校验
 * @date 2020/3/19 15:58
 **/
@Configuration
public class OAuth2PermissionEvaluator implements PermissionEvaluator {


    @Override
    public boolean hasPermission(Authentication authentication, Object url, Object per) {
        AuthUserDetails userDetail = (AuthUserDetails) authentication.getPrincipal();
        if (userDetail.getAuthentication().getRoleSigns().contains("super_admin")) {
            return true;
        }
        Set<AuthPermission> authResources = userDetail.getAuthentication().getPermissions();
        for (AuthPermission authResource : authResources) {
            String resource = authResource.getSign();
            if (url.equals(authResource.getUrl()) && per.equals(resource)) {
                return true;
            }

        }
        return false;

    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }


    //    private List<Permission> findPermission(UserInfo userInfo) {
    //        List<UserRole> userRoles = this.userRoleService.select(QueryParam.build("user_id", userInfo.getId()));
    //        final List<Integer> rolePermissions = this.rolePermissionService
    //                .select(QueryParam.build("role_id"
    //                        , TermEnum.in
    //                        , userRoles
    //                                .stream()
    //                                .map(UserRole::getRoleId)
    //                                .collect(Collectors.toSet())))
    //                .stream()
    //                .map(RolePermission::getPermissionId)
    //                .collect(Collectors.toList());
    //        return this.permissionService
    //                .select(QueryParam.build("id"
    //                        , TermEnum.in
    //                        , rolePermissions));
    //    }

}
