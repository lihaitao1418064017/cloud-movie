package org.lht.boot.mq.activemq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description ActivemqProperties:
 * @date 2020/2/28 10:10
 **/
@Configuration
@ConfigurationProperties(value = "activemq.cluster")
@Data
public class ActivemqProperties {

    private String username;

    private String password;

    private String url;

    private int acknowledge = 1;

    private Boolean isAcknowledge = true;

}
