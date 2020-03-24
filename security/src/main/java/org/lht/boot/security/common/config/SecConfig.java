package org.lht.boot.security.common.config;

import org.lht.boot.security.common.session.SecInvalidSessionStrategy;
import org.lht.boot.security.handler.SecAuthenticationAccessDeniedHandler;
import org.lht.boot.security.handler.SecAuthenticationLogoutHandler;
import org.lht.boot.security.handler.SecAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.session.InvalidSessionStrategy;

/**
 * @author LiHaitao
 * @description SecConfig:
 * @date 2020/3/23 18:26
 **/
@Configuration
public class SecConfig {

    @Autowired
    private SecProperties secProperties;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    // 使用 javaconfig 的方式配置是为了注入 sessionRegistry
    @Bean
    public SecAuthenticationSuccessHandler secAuthenticationSucessHandler() {
        SecAuthenticationSuccessHandler secAuthenticationSuccessHandler = new SecAuthenticationSuccessHandler();
        secAuthenticationSuccessHandler.setSessionRegistry(sessionRegistry());
        return secAuthenticationSuccessHandler;
    }

    // 配置登出处理器
    @Bean
    public SecAuthenticationLogoutHandler logoutHandler() {
        SecAuthenticationLogoutHandler secAuthenticationLogoutHandler = new SecAuthenticationLogoutHandler();
        secAuthenticationLogoutHandler.setSessionRegistry(sessionRegistry());
        return secAuthenticationLogoutHandler;
    }

    @Bean
    public InvalidSessionStrategy invalidSessionStrategy() {
        SecInvalidSessionStrategy secInvalidSessionStrategy = new SecInvalidSessionStrategy();
        secInvalidSessionStrategy.setSecProperties(secProperties);
        return secInvalidSessionStrategy;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new SecAuthenticationAccessDeniedHandler();
    }

    //    /**
    //     * XssFilter Bean
    //     */
    //    @Bean  todo
    //    @SuppressWarnings({"unchecked", "rawtypes"})
    //    public FilterRegistrationBean xssFilterRegistrationBean() {
    //        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    //                filterRegistrationBean.setFilter(new XssFilter());
    //        filterRegistrationBean.setOrder(1);
    //        filterRegistrationBean.setEnabled(true);
    //        filterRegistrationBean.addUrlPatterns("/*");
    //        Map<String, String> initParameters = new HashMap<>();
    //        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
    //        initParameters.put("isIncludeRichText", "true");
    //        filterRegistrationBean.setInitParameters(initParameters);
    //        return filterRegistrationBean;
    //    }


    @Bean
    public RedirectStrategy redirectStrategy() {
        return new DefaultRedirectStrategy();
    }

    @Bean
    public RequestCache requestCache() {
        return new HttpSessionRequestCache();
    }
}
