package org.lht.boot.cache.common.config;

import org.lht.boot.cache.producer.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
    public <K, V> KafkaSender<K, V> kvKafkaSender() {
        return new KafkaSender<K, V>(producerFactory);
    }


}
