package org.lht.boot.mq.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author LiHaitao
 * @description KafkaProducerListener:发送消息监听器
 * @date 2020/1/16 14:07
 **/
@Slf4j
public class KafkaProducerListener<K, V> implements org.springframework.kafka.support.ProducerListener<K, V> {


    @Override
    public void onSuccess(String topic, Integer partition, K key, V value, RecordMetadata recordMetadata) {
        log.info("Send successfully [topic:{},partition:{},key:{},value:{},recordMetadata:{}]", topic, partition, key, value, recordMetadata);
    }

    @Override
    public void onError(String topic, Integer partition, K key, V value, Exception exception) {
        log.error("Send error [topic:{},partition:{},key:{},value:{},exception:{}]", topic, partition, key, value, exception);

    }


}
