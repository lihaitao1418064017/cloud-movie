package org.lht.boot.security.resource.config;

import org.lht.boot.security.config.config.OAuth2ResourceServerConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 资源服务器访问权限配置
 */
@Configuration
public class WebSecurityConfig extends OAuth2ResourceServerConfig {
    // TODO: 2020/3/27 shan 
    //    @Override
    //    public void configure(HttpSecurity http) throws Exception {
    //        super.configure(http);
    //
    //        http
    //                .authorizeRequests()
    //                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
    //                .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
    //                .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
    //                .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
    //                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
    //                .and()
    //
    //                .headers().addHeaderWriter((request, response) -> {
    //            response.addHeader("Access-Control-Allow-Origin", "*");
    //            if (request.getMethod().equals("OPTIONS")) {
    //                response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
    //                response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
    //            }
    //        });
    //
    //    }

}
