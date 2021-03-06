package org.lht.boot.security.client.common.config;

import org.lht.boot.security.client.service.OAuth2PermissionEvaluator;
import org.lht.boot.security.common.config.SecProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;


/**
 * @description: 自定义权限
 * @author: LiHaitao
 * @date: 2020/3/26 10:57
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableConfigurationProperties(SecProperties.class)
public class OAuth2ClientGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    private SecProperties secProperties;

    @Autowired
    private OAuth2PermissionEvaluator OAuth2PermissionEvaluator;


    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(OAuth2PermissionEvaluator);
        expressionHandler.setDefaultRolePrefix(secProperties.getDefaultRolePrefix());
        return expressionHandler;
    }

}
