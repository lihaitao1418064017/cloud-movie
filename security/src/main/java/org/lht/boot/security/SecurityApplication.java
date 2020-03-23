package org.lht.boot.security;

import org.lht.boot.security.common.config.SecProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan("org.lht.boot.security.dao")
@SpringBootApplication
@EnableConfigurationProperties(SecProperties.class)
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
