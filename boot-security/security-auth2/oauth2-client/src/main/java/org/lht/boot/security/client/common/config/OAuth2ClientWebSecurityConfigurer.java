package org.lht.boot.security.client.common.config;

import org.lht.boot.security.common.config.SecWebSecurityConfigurer;
import org.lht.boot.security.service.SecUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author LiHaitao
 * @description SecResourceWebSecurityConfigurer:
 * @date 2020/4/30 15:30
 **/
@Configuration
@Order(10)
public class OAuth2ClientWebSecurityConfigurer extends SecWebSecurityConfigurer {

    @Autowired
    private SecUserDetailService secUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(secUserDetailService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

}
