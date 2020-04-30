package org.lht.boot.security.client.service;

import org.lht.boot.security.client.entity.OAuth2Token;

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
}
