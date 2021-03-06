package org.lht.boot.security.server.common.config;

import org.lht.boot.security.common.config.SecWebSecurityConfigurer;
import org.lht.boot.security.server.service.OAuth2SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author LiHaitao
 * @description OAuth2ServerWebSecurityConfig:security配置
 * @date 2020/5/6 15:12
 **/
@Configuration
@Order(90)
@SuppressWarnings("all")
public class OAuth2ServerWebSecurityConfig extends SecWebSecurityConfigurer {


    @Autowired
    private OAuth2SecurityUserDetailService OAuth2SecurityUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(OAuth2SecurityUserDetailService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }


}
