package org.lht.boot.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }

}
