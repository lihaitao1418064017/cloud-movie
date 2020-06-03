package org.lht.boot.security.server.domain.entity;

import lombok.Data;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.security.resource.entity.Role;
import org.lht.boot.security.resource.entity.UserInfo;

import java.util.Set;

/**
 * @author LiHaitao
 * @description OAuth2UserAuthentication:登录用户信息
 * @date 2020/5/28 15:31
 **/
@Data
public class OAuth2UserAuthentication {


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
    private UserInfo userInfo;


    /**
     * 令牌信息
     */
    private AccessToken accessToken;


}
