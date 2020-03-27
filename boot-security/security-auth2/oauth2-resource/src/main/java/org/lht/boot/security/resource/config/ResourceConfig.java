package org.lht.boot.security.resource.config;

import org.lht.boot.security.config.config.AbstractSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/22 5:02 PM
 **/
@Configuration
public class ResourceConfig extends AbstractSecurityConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
