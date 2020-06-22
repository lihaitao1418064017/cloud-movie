package org.lht.boot.security.server.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author LiHaitao
 * @description OAuth2AuthenticationService:
 * @date 2020/6/22 17:02
 **/
public class OAuth2AuthenticationService {


    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    public static String currentUsername() {
        return (String) getAuthentication().getPrincipal();
    }


}
