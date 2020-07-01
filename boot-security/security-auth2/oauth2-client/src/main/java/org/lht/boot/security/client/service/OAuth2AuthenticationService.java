package org.lht.boot.security.client.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author LiHaitao
 * @description OAuth2AuthenticationService:获取当前登录用户权限信息
 * @date 2020/6/22 17:02
 **/
public class OAuth2AuthenticationService {


    /**
     * 获取当前登录用户权限信息
     *
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 获取当前登录用户名
     *
     * @return
     */
    public static String currentUsername() {
        return (String) getAuthentication().getPrincipal();
    }


}
