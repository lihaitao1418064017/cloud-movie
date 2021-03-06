package org.lht.boot.security.server.domain;

import lombok.Data;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.security.resource.entity.Role;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.server.domain.entity.AccessToken;

import java.util.Set;

/**
 * @author LiHaitao
 * @description OAuth2UserInfoAuthentication:登录用户信息
 * @date 2020/5/28 15:31
 **/
@Data
public class OAuth2UserInfoAuthentication {


    /**
     * 角色
     */
    private Set<Role> roles;

    /**
     * 权限信息
     */
    private Set<Permission> permissions;


    /**
     * 用户基本信息
     */
    private UserInfo user;


    /**
     * 令牌信息
     */
    private AccessToken accessToken;


}
