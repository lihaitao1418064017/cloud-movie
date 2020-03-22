package org.lht.boot.mq.activemq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author LiHaitao
 * @description ActivemqProperties:
 * @date 2020/2/28 10:10
 **/
@Configuration
@ConfigurationProperties(value = "activemq.consumer")
@Data
@Component
public class ActivemqConsumerProperties {

    private String username;

    private String password;

    private String url;

    private int acknowledge = 1;

    private Boolean isAcknowledge = true;

}
