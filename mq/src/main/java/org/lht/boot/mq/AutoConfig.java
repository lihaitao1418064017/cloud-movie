package org.lht.boot.mq;

import org.lht.boot.mq.activemq.config.ActivemqProperties;
import org.lht.boot.mq.kafka.config.KafkaClusterProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description AutoConfig:
 * @date 2020/1/19 15:49
 **/
@Configuration
@EnableConfigurationProperties({KafkaClusterProperties.class, ActivemqProperties.class})
public class AutoConfig {
}
