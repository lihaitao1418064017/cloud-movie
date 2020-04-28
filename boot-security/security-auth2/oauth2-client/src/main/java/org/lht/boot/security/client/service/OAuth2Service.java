package org.lht.boot.security.client.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiHaitao
 * @description OAuth2Service:
 * @date 2020/4/28 19:44
 **/
public interface OAuth2Service {
    String authorize(HttpServletRequest request, HttpServletResponse response);
}
