package org.lht.boot.security.client.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.lht.boot.lang.util.RestTemplateUtil;
import org.lht.boot.security.client.common.config.OAuth2ClientConfigProperties;
import org.lht.boot.security.client.common.util.OAuth2RequestUtil;
import org.lht.boot.security.client.entity.OAuth2Token;
import org.lht.boot.security.client.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiHaitao
 * @description OAuth2ServiceImpl:
 * @date 2020/4/28 19:44
 **/
@Service
@EnableConfigurationProperties(OAuth2ClientConfigProperties.class)
public class OAuth2ServiceImpl implements OAuth2Service {

    @Autowired
    private OAuth2ClientConfigProperties oAuth2ClientConfigProperties;


    @Override
    public String authorize(HttpServletRequest request, HttpServletResponse response) {
        //请求授权参数
        Map<String, String> authParameters = new HashMap<>();
        authParameters.put("client_id", oAuth2ClientConfigProperties.getClientId());
        authParameters.put("response_type", oAuth2ClientConfigProperties.getResponseType());
        authParameters.put("redirect_uri", OAuth2RequestUtil.getEncodedUrl(oAuth2ClientConfigProperties.getClientUri()) + "/oauth2/callback");
        return OAuth2RequestUtil.buildAuthorizeUrl(oAuth2ClientConfigProperties.getServerUri(), authParameters);
    }

    @Override
    public OAuth2Token getOAuth2Token(String authorizationCode) {
        String authBase64 =
                OAuth2RequestUtil.encodeCredentials(oAuth2ClientConfigProperties.getClientId(), oAuth2ClientConfigProperties.getClientSecret());
        OAuth2Token oAuth2Token = RestTemplateUtil.exchangeHandleBasicHeader(oAuth2ClientConfigProperties.getServerUri() + "/oauth/token"
                , OAuth2RequestUtil.getBasicHeader(authBase64)
                , OAuth2RequestUtil.getAccessTokenBody(oAuth2ClientConfigProperties.getGrantType()
                        , oAuth2ClientConfigProperties.getScope()
                        , authorizationCode
                        , oAuth2ClientConfigProperties.getClientUri() + "/oauth2/callback"
                )
                , HttpMethod.POST
                , OAuth2Token.class);
        if (ObjectUtil.isNotNull(oAuth2Token)) {
            return oAuth2Token;
        }
        throw new RuntimeException("error trying to retrieve access token");
    }
}
