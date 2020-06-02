package org.lht.boot.security.client.common.config;

import org.lht.boot.security.common.config.SecWebSecurityConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author LiHaitao
 * @description SecResourceWebSecurityConfigurer:
 * @date 2020/4/30 15:30
 **/
@Configuration
@Order(90)
public class OAuth2ClientWebSecurityConfigurer extends SecWebSecurityConfigurer {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

}
