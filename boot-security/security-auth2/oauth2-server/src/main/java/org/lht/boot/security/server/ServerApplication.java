package org.lht.boot.security.server;

import org.lht.boot.security.config.config.annotation.EnableAuthJWTTokenStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"org.lht.boot.security"})
@EnableAuthJWTTokenStore    // 使用 JWT 存储令牌
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})

public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
