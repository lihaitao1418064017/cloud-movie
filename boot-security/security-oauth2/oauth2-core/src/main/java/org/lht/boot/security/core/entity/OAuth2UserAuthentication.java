package org.lht.boot.security.core.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author LiHaitao
 * @description OAuth2UserAuthentication:用户权限信息
 * @date 2020/5/28 15:31
 **/
@Data
public class OAuth2UserAuthentication {


    private Set<AuthRole> roles;


    private Set<AuthResource> permissions;


    private AuthUser user;


    private OAuth2Token oAuth2Token;

}
