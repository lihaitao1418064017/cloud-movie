package org.lht.boot.mq.kafka.producer;


import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * @author LiHaitao
 * @description KafkaSender:Kafka发送者
 * @date 2020/1/16 13:51
 **/
public class KafkaSender<K, V> extends KafkaTemplate<K, V> {

    public KafkaSender(ProducerFactory<K, V> producerFactory, KafkaProducerListener<K, V> kvKafkaProducerListener) {
        super(producerFactory);
        KafkaProducerListener<K, V> kafkaProducerListener = kvKafkaProducerListener;
        setProducerListener(kafkaProducerListener);
    }

    public KafkaSender(ProducerFactory<K, V> producerFactory) {
        super(producerFactory);
        KafkaProducerListener<K, V> kafkaProducerListener = new KafkaProducerListener<>();
        setProducerListener(kafkaProducerListener);
    }

    public KafkaSender(ProducerFactory<K, V> producerFactory, boolean autoFlush) {
        super(producerFactory, autoFlush);
    }


    /**
     * 将key和value对象转化为json
     *
     * @param topic
     * @param key
     * @param data
     * @return
     */
    public ListenableFuture<SendResult<K, V>> sendByJsonStr(String topic, K key, @Nullable V data) {
        String keyStr = JSONObject.toJSONString(key);
        ProducerRecord<K, V> producerRecord = new ProducerRecord(topic, keyStr, covertMessage(data));
        return this.doSend(producerRecord);
    }

    /**
     * 自动将对象转化为Json
     *
     * @param topic
     * @param data
     * @return
     */
    public ListenableFuture<SendResult<K, V>> sendByJsonStr(String topic, @Nullable V data) {
        ProducerRecord<K, V> producerRecord = new ProducerRecord(topic, covertMessage(data));
        return this.doSend(producerRecord);
    }

    /**
     * 转换消息，填充消息
     *
     * @param data
     * @return
     */
    private String covertMessage(V data) {
        try {
            String dataStr = JSONObject.toJSONString(data);
            JSONObject jsonObject = JSONObject.parseObject(dataStr);
            jsonObject.put("messageId", UUID.randomUUID().toString());
            jsonObject.put("timestamp", System.currentTimeMillis());
            return jsonObject.toJSONString();
        } catch (Exception e) {
            return null;
        }
    }

}
