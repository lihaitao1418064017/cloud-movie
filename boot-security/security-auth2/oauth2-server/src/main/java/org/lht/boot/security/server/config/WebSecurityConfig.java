package org.lht.boot.security.server.config;

import org.lht.boot.security.config.config.AbstractSecurityConfig;
import org.lht.boot.security.config.constants.AuthoritiesEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

/**

 * @description: 权限配置
 */
@Configuration
public class WebSecurityConfig extends AbstractSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

}
