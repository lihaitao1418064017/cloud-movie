package org.lht.boot.mq.kafka.config;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.lht.boot.mq.kafka.entity.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: kafka消费配置类
 * @Author: Lihaitao
 * @Date: 2020/1/16 16:01
 * @UpdateUser:
 * @UpdateRemark:
 */
@Configuration
@EnableKafka
@EnableConfigurationProperties({KafkaProperties.class, KafkaMultiProperties.class})
public class KafkaConsumerConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired
    private KafkaMultiProperties kafkaMultiProperties;


    @Bean("batchFactory")
    public KafkaListenerContainerFactory<?> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        //并发数
        factory.setConcurrency(1);
        //设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG 默认 500
        factory.setBatchListener(true);
        //设置提交偏移量的方式
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
        return factory;
    }

    @Bean(name = "basicKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> basicKafkaListenerContainerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaMultiProperties.getBootstrapServers());
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaValueDeserializer.class);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaMultiProperties.getGroupId());
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaMultiProperties.getAutoOffsetReset());
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaMultiProperties.getEnableAutoCommit());
        configProps.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaMultiProperties.getSessionTimeoutMs());
        ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        ConsumerFactory<String, KafkaMessage> basicConsumerFactory =
                new DefaultKafkaConsumerFactory(configProps, new StringDeserializer(), new KafkaValueDeserializer());
        factory.setConsumerFactory(basicConsumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        return factory;
    }


    @Bean("singleFactory")
    public KafkaListenerContainerFactory<?> singleFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        Map<String, Object> stringObjectMap = kafkaProperties.buildConsumerProperties();
        stringObjectMap.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, Lists.newArrayList(kafkaMultiProperties.getBootstrapServers()));
        stringObjectMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaMultiProperties.getEnableAutoCommit());
        stringObjectMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaMultiProperties.getAutoOffsetReset());
        stringObjectMap.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaMultiProperties.getGroupId());
        //每一批数量
        stringObjectMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaMultiProperties.getSessionTimeoutMs());
        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(stringObjectMap);
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        //设置提交偏移量的方式
        factory.getContainerProperties().
                setAckMode(ContainerProperties.AckMode.RECORD);
        return factory;
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> stringObjectMap = kafkaProperties.buildConsumerProperties();
        stringObjectMap.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, Lists.newArrayList(kafkaMultiProperties.getBootstrapServers()));
        stringObjectMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaMultiProperties.getEnableAutoCommit());
        stringObjectMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaMultiProperties.getSessionTimeoutMs());
        stringObjectMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaMultiProperties.getMaxPollRecords());
        return stringObjectMap;
    }

    private ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

}