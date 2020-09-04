package org.lht.boot.security.server.common.config;

import lombok.extern.slf4j.Slf4j;
import org.lht.boot.cache.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * AccessToken添加字段，并保存accessToken
 *
 * @author lht
 * @date 2020-07-02
 */
@Slf4j
@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    private RedisUtil<String, String> redisUtil;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("user-info:{}", user);
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("username", user.getUsername());
        redisUtil.set(accessToken.getValue(), user.getUsername(), 3600);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
