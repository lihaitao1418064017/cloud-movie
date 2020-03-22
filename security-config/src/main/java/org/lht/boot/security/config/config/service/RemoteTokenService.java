package org.lht.boot.security.config.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @description: 通过访问远程授权服务器 check_token 端点验证令牌
 */
public class RemoteTokenService {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    private AuthorizationServerProperties authorizationServerProperties;


    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(authorizationServerProperties.getCheckTokenAccess());
        if (StringUtils.isEmpty(oAuth2ClientProperties.getClientId())) {
            remoteTokenServices.setClientId(oAuth2ClientProperties.getClientId());
        }
        if (StringUtils.isEmpty(oAuth2ClientProperties.getClientId())){
            remoteTokenServices.setClientSecret(oAuth2ClientProperties.getClientSecret());
        }
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        return remoteTokenServices;
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }

}
