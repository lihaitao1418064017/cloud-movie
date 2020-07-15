package org.lht.boot.security.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"org.lht.boot.web", "org.lht.boot.security"})
@EnableDiscoveryClient
@MapperScan(basePackages = {"org.lht.boot.security.resource.dao", "org.lht.boot.security.server.dao"})
public class OAuth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2ServerApplication.class, args);
    }

}
