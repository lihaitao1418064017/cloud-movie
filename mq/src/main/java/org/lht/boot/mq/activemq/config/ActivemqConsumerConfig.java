package org.lht.boot.mq.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @author LiHaitao
 * @description ActivemqConsumerConfig:增加一个消费配置类
 * @date 2020/3/2 14:05
 **/
@Configuration
public class ActivemqConsumerConfig {


    @Autowired
    private ActivemqConsumerProperties activemqConsumerProperties;


    private ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(activemqConsumerProperties.getUsername()
                , activemqConsumerProperties.getPassword()
                , activemqConsumerProperties.getUrl());
    }

    @Bean("queueListener")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory() {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean("topicListener")
    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory() {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(true);
        return factory;
    }
}
