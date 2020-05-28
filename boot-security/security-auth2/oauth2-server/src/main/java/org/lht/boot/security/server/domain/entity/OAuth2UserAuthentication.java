package org.lht.boot.security.server.domain.entity;

import lombok.Data;
import org.lht.boot.security.entity.Permission;
import org.lht.boot.security.entity.Role;
import org.lht.boot.security.entity.UserInfo;


import java.util.Set;

/**
 * @author LiHaitao
 * @description OAuth2UserAuthentication:
 * @date 2020/5/28 15:31
 **/
@Data
public class OAuth2UserAuthentication {


    private Set<Role> roles;


    private Set<Permission> permissions;


    private UserInfo userInfo;


    private AccessToken accessToken;


}
