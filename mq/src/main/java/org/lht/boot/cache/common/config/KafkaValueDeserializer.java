package org.lht.boot.cache.common.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Deserializer;
import org.lht.boot.cache.common.entity.KafkaMessage;

/**
 * @author LiHaitao
 * @description KafkaValueDeserializer:
 * @date 2020/1/16 16:59
 **/
public class KafkaValueDeserializer implements Deserializer<KafkaMessage> {


    @Override
    public KafkaMessage deserialize(String topic, byte[] bytes) {
        String data = new String(bytes);
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setTopic(topic);
        kafkaMessage.setBody(JSONObject.parseObject(data));
        return kafkaMessage;
    }


}
