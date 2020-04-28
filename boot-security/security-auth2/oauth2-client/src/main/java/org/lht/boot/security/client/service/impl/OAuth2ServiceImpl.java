package org.lht.boot.security.client.service.impl;

import org.lht.boot.security.client.common.config.OAuth2ClientProperties;
import org.lht.boot.security.client.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiHaitao
 * @description OAuth2ServiceImpl:
 * @date 2020/4/28 19:44
 **/
@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;


    @Override
    public String authorize(HttpServletRequest request, HttpServletResponse response) {

        //请求授权参数
        Map<String, String> authParameters = new HashMap<>();
        authParameters.put("client_id", oAuth2ClientProperties.getClientId());
        authParameters.put("response_type", oAuth2ClientProperties.getResponseType());
        authParameters.put("redirect_uri", getEncodedUrl(oAuth2ClientProperties.getClientUri()));


        return null;
    }


    private String getEncodedUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
