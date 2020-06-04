package org.lht.boot.security.client.service;

import org.lht.boot.security.client.entity.OAuth2Token;
import org.lht.boot.security.client.entity.OAuth2UserAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiHaitao
 * @description OAuth2Service: oauth2认证
 * @date 2020/4/28 19:44
 **/
public interface OAuth2Service {
    /**
     * 获取code
     *
     * @param request
     * @param response
     * @return
     */
    String authorize(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取accessToken
     *
     * @param authorizationCode
     * @return
     */
    OAuth2Token getOAuth2Token(String authorizationCode);


    /**
     * 当前用户登录
     *
     * @param oAuth2Token
     */
    void currentUserLogin(OAuth2Token oAuth2Token, HttpServletRequest request);


    /**
     * 获取登录用户信息
     *
     * @param auth2Token
     * @return
     */
    OAuth2UserAuthentication getAuthenticationByAccessToken(OAuth2Token auth2Token);
}
