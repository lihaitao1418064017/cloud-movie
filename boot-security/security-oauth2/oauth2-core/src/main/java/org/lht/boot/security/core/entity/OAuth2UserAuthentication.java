package org.lht.boot.security.core.entity;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Sets;
import lombok.Data;
import org.lht.boot.security.entity.AuthPermission;
import org.lht.boot.security.entity.AuthRole;
import org.lht.boot.security.entity.AuthUser;
import org.lht.boot.security.entity.Authentication;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description OAuth2UserAuthentication:用户权限信息
 * @date 2020/5/28 15:31
 **/
@Data
@SuppressWarnings("all")
public class OAuth2UserAuthentication implements Authentication {


    private Set<AuthRole> roles;

    private Set<AuthPermission> permissions;

    private Set<String> permissionSigns;

    private AuthUser user;

    private OAuth2Token oAuth2Token;

    protected transient Set<String> roleSigns;

    private String password;


    @Override
    public Set<String> getRoleSigns() {
        if (roleSigns == null) {
            if (CollectionUtils.isNotEmpty(roles)) {
                synchronized (this) {
                    roleSigns = roles.stream().map(AuthRole::getSign).collect(Collectors.toSet());
                }
            } else {
                roleSigns = Sets.newHashSet();
            }
        }
        return roleSigns;
    }

    @Override
    public Set<String> getPermissionSigns() {
        if (permissionSigns == null) {
            if (CollectionUtils.isNotEmpty(permissions)) {
                synchronized (this) {
                    permissionSigns = permissions.stream().map(AuthPermission::getSign).collect(Collectors.toSet());
                }
            } else {
                permissionSigns = Sets.newHashSet();
            }
        }
        return permissionSigns;
    }


}
