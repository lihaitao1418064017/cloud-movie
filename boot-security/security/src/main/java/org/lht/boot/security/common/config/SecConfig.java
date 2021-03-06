package org.lht.boot.security.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.lht.boot.security.handler.SecAuthenticationAccessDeniedHandler;
import org.lht.boot.security.handler.SecAuthenticationLogoutHandler;
import org.lht.boot.security.handler.SecAuthenticationSuccessHandler;
import org.lht.boot.security.handler.SecRestLogoutSuccessHandler;
import org.lht.boot.security.session.SecInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

import javax.sql.DataSource;

/**
 * @author LiHaitao
 * @description SecConfig: security通用配置
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

    @Bean
    public SecAuthenticationSuccessHandler secAuthenticationSucessHandler() {
        SecAuthenticationSuccessHandler secAuthenticationSuccessHandler = new SecAuthenticationSuccessHandler();
        secAuthenticationSuccessHandler.setSessionRegistry(sessionRegistry());
        return secAuthenticationSuccessHandler;
    }

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


    @Bean
    public SecRestLogoutSuccessHandler secRestLogoutSuccessHandler() {
        return new SecRestLogoutSuccessHandler();
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


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }
    //
    //    @Bean(name = "jdbcTemplate")
    //    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
    //        return new NamedParameterJdbcTemplate(dataSource());
    //    }


}
