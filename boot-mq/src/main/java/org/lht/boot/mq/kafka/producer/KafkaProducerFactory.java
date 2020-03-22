package org.lht.boot.mq.kafka.producer;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.lht.boot.mq.kafka.config.KafkaValueDeserializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Kafka生产者工厂
 * @Author: LiHaitao
 * @CreateDate: 2020/1/17 10:44  //相关配置请看help.md
 */
public class KafkaProducerFactory {

    private volatile static KafkaProducerFactory instance;

    /**
     * 单例
     *
     * @return KafkaProducerFactory
     */
    public static KafkaProducerFactory newInstance() {
        if (null == instance) {
            synchronized (KafkaProducerFactory.class) {
                if (null == instance) {
                    instance = new KafkaProducerFactory();
                }
            }
        }
        return instance;
    }

    private List<String> bootstrapServers;

    /**
     * 重试次数
     */
    private int retries = 0;

    /**
     * 批量数
     */
    private int batchSize = 16384;

    /**
     * 等待batch满的等待时间
     */
    private int lingerMs = 1;

    /**
     * 缓冲区内存配置
     */
    private int bufferMemory = 33554432;


    public KafkaProducerFactory bootstrapServers(List<String> bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
        return this;
    }

    public KafkaProducerFactory retries(int retries) {
        this.retries = retries;
        return this;
    }

    public KafkaProducerFactory batchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public KafkaProducerFactory lingerMs(int lingerMs) {
        this.lingerMs = lingerMs;
        return this;
    }

    public KafkaProducerFactory bufferMemory(int bufferMemory) {
        this.bufferMemory = bufferMemory;
        return this;
    }

    private ProducerFactory<String, String> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaValueDeserializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    /**
     * build一个kafka自定义工厂
     *
     * @return
     */
    public KafkaTemplate build() {
        if (CollectionUtil.isEmpty(bootstrapServers)) {
            return null;
        }
        return new KafkaTemplate<>(producerFactory());
    }
}