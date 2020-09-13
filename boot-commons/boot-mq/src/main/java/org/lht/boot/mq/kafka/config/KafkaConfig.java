package org.lht.boot.mq.kafka.config;

import org.lht.boot.mq.kafka.producer.KafkaProducerListener;
import org.lht.boot.mq.kafka.producer.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;

/**
 * @author LiHaitao
 * @description KafkaConfig: Kafka生产者配置类
 * @date 2020/1/16 14:18
 **/
@Configuration
public class KafkaConfig {

    @Autowired
    private ProducerFactory producerFactory;

    @Bean
    @ConditionalOnProperty(prefix = "spring.kafka", name = "enable", havingValue = "true", matchIfMissing = false)
    public <K, V> KafkaSender<K, V> kafkaSender() {
        return new KafkaSender<K, V>(producerFactory, new KafkaProducerListener<>());
    }


}
