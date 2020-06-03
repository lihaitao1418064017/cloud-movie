package org.lht.boot.security.client.service;

import org.lht.boot.security.client.entity.AuthResource;
import org.lht.boot.security.client.entity.AuthUserDetails;
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
        Set<AuthResource> authResources = (Set<AuthResource>) userDetail.getAuthorities();
        for (AuthResource authResource : authResources) {
            String resource = authResource.getAuthority();
            if (url.equals(resource) && per.equals(authResource.getUrl())) {
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
