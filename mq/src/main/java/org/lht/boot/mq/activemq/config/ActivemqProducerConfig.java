package org.lht.boot.mq.activemq.config;

import org.lht.boot.mq.activemq.producer.ActivemqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

/**
 * @author LiHaitao
 * @description ActivemqProducerConfig:发送者配置类
 * @date 2020/2/28 13:08
 **/
@Configuration
public class ActivemqProducerConfig {


    @Autowired
    private ConnectionFactory connectionFactory;


    @Bean
    public ActivemqSender activemqSender() {
        return new ActivemqSender(connectionFactory);
    }
}
