package org.lht.boot.security.client.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author LiHaitao
 * @description OAuth2UserAuthentication:
 * @date 2020/5/28 15:31
 **/
@Data
public class OAuth2UserAuthentication {


    private Set<AuthRole> roles;


    private Set<AuthResource> resources;


    private AuthUser user;


    private OAuth2Token oAuth2Token;

}
