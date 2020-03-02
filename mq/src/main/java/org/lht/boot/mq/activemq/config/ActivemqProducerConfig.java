package org.lht.boot.mq.activemq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description ActivemqProducerConfig:发送者配置类
 * @date 2020/2/28 13:08
 **/
@Configuration
public class ActivemqProducerConfig {

    @Autowired
    private ActivemqProperties activemqProperties;

    //
    //    @Bean
    //    public ConnectionFactory connectionFactory() {
    //        return new ActiveMQConnectionFactory(username, password, brokerUrl);
    //    }

}
