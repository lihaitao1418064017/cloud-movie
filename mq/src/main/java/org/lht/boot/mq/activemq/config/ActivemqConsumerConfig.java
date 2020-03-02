package org.lht.boot.mq.activemq.config;

/**
 * @author LiHaitao
 * @description ActivemqConsumerConfig:
 * @date 2020/3/2 14:05
 **/
public class ActivemqConsumerConfig {


    //    // 在Queue模式中，对消息的监听需要对containerFactory进行配置
    //    @Bean("queueListener")
    //    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
    //        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
    //        factory.setConnectionFactory(connectionFactory);
    //        factory.setPubSubDomain(false);
    //        return factory;
    //    }
    //
    //    //在Topic模式中，对消息的监听需要对containerFactory进行配置
    //    @Bean("topicListener")
    //    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
    //        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
    //        factory.setConnectionFactory(connectionFactory);
    //        factory.setPubSubDomain(true);
    //        return factory;
    //    }
}
