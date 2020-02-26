package org.lht.boot.mq.kafka.producer;


import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author LiHaitao
 * @description KafkaSender:Kafka发送者
 * @date 2020/1/16 13:51
 **/
public class KafkaSender<K, V> extends KafkaTemplate<K, V> {

    private KafkaProducerListener<K, V> kafkaProducerListener;

    public KafkaSender(ProducerFactory<K, V> producerFactory) {
        super(producerFactory);
        kafkaProducerListener = new KafkaProducerListener();
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
        String dataStr = JSONObject.toJSONString(data);
        ProducerRecord<K, V> producerRecord = new ProducerRecord(topic, keyStr, dataStr);
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
        String dataStr = JSONObject.toJSONString(data);
        ProducerRecord producerRecord = new ProducerRecord(topic, dataStr);
        return this.doSend(producerRecord);
    }


}
