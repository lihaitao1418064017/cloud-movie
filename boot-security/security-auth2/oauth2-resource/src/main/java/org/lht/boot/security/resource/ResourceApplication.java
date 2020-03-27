package org.lht.boot.security.resource;

import org.lht.boot.security.config.config.annotation.EnableResJWTTokenStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableResJWTTokenStore //OAuth2 使用 JWT 解析令牌
public class ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

}
