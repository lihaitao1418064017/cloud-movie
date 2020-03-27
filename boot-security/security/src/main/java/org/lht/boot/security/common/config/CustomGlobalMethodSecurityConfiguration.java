package org.lht.boot.security.common.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.lht.boot.security.handler.SecPermissionEvaluator;
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
public class CustomGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    private SecProperties secProperties;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new SecPermissionEvaluator());
        expressionHandler.setDefaultRolePrefix(secProperties.getDefaultRolePrefix());
        return expressionHandler;
    }

    /**
     * 前置通知
     */
    @Around("execution(* @annotation(com.kedacom.ctsp.authz.Authorize))")
    public void aroundAuthorize(ProceedingJoinPoint joinPoint) throws Throwable {
        //        super.methodSecurityInterceptor().invoke(new MethodInvocationAdapter(joinPoint));
    }

}
