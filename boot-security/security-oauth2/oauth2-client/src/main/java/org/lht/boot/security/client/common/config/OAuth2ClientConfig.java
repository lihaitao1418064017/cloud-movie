package org.lht.boot.security.client.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description OAuth2ClientConfig: 配置类
 * @date 2020/4/30 15:44
 **/
@Configuration
@EnableConfigurationProperties(OAuth2ClientConfigProperties.class)
public class OAuth2ClientConfig {

}
