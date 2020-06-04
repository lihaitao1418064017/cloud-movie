package org.lht.boot.security.client.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.lht.boot.lang.util.RestTemplateUtil;
import org.lht.boot.security.client.common.config.OAuth2ClientConfigProperties;
import org.lht.boot.security.client.common.util.OAuth2RequestUtil;
import org.lht.boot.security.client.entity.AuthUserDetails;
import org.lht.boot.security.client.entity.OAuth2Token;
import org.lht.boot.security.client.entity.OAuth2UserAuthentication;
import org.lht.boot.security.client.service.OAuth2Service;
import org.lht.boot.security.client.service.OAuth2UserService;
import org.lht.boot.web.api.param.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private OAuth2ClientConfigProperties oAuth2ClientConfigProperties;

    @Autowired
    private OAuth2UserService oAuth2ServerService;

    @Value("${server.session.timeout:86400}")
    private int expireTime;

    @Override
    public String authorize(HttpServletRequest request, HttpServletResponse response) {
        //请求授权参数
        Map<String, String> authParameters = new HashMap<>();
        authParameters.put("client_id", oAuth2ClientConfigProperties.getClientId());
        authParameters.put("response_type", oAuth2ClientConfigProperties.getResponseType());
        authParameters.put("redirect_uri", OAuth2RequestUtil.getEncodedUrl(oAuth2ClientConfigProperties.getClientUri()) + "oauth2/callback");

        return OAuth2RequestUtil.buildAuthorizeUrl(oAuth2ClientConfigProperties.getServerUri() + "oauth/authorize", authParameters);
    }


    @Override
    public OAuth2Token getOAuth2Token(String authorizationCode) {
        String authBase64 = OAuth2RequestUtil.encodeCredentials(oAuth2ClientConfigProperties.getClientId(), oAuth2ClientConfigProperties.getClientSecret());
        OAuth2Token oAuth2Token = RestTemplateUtil.exchangeHandleBasicHeader(oAuth2ClientConfigProperties.getServerUri()
                        + "oauth/token"
                , OAuth2RequestUtil.getBasicHeader(authBase64)
                , OAuth2RequestUtil.getAccessTokenBody(authorizationCode
                        , oAuth2ClientConfigProperties.getScope()
                        , "http://localhost:8081/oauth2/callback"
                        , oAuth2ClientConfigProperties.getGrantType()
                        , oAuth2ClientConfigProperties.getClientId()
                        , oAuth2ClientConfigProperties.getClientSecret()
                )
                , HttpMethod.POST
                , OAuth2Token.class);
        if (ObjectUtil.isNotNull(oAuth2Token)) {
            return oAuth2Token;
        }
        throw new RuntimeException("error trying to retrieve access token");
    }

    /**
     * 当前用户登录
     *
     * @param oAuth2Token
     */
    @Override
    public void currentUserLogin(OAuth2Token oAuth2Token, HttpServletRequest request) {
        OAuth2UserAuthentication authenticationByAccessToken = getAuthenticationByAccessToken(oAuth2Token);
        authenticationByAccessToken.setOAuth2Token(oAuth2Token);
        AuthUserDetails secUserDetails = new AuthUserDetails(authenticationByAccessToken);
        PreAuthenticatedAuthenticationToken authentication =
                new PreAuthenticatedAuthenticationToken(secUserDetails, oAuth2Token, secUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(expireTime);
    }

    @Override
    public OAuth2UserAuthentication getAuthenticationByAccessToken(OAuth2Token auth2Token) {
        R<OAuth2UserAuthentication> loginUserByAccessToken = oAuth2ServerService.getLoginUserByAccessToken(oAuth2ClientConfigProperties.getClientId()
                , oAuth2ClientConfigProperties.getClientSecret()
                , auth2Token.getAccessToken());
        return loginUserByAccessToken.getResult();
    }
}
