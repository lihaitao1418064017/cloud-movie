package org.lht.boot.mq.common.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Deserializer;
import org.lht.boot.mq.common.entity.KafkaMessage;

/**
 * @author LiHaitao
 * @description ValueDeserializer:
 * @date 2020/1/16 16:59
 **/
public class ValueDeserializer implements Deserializer<KafkaMessage> {


    @Override
    public KafkaMessage deserialize(String topic, byte[] bytes) {
        String data = new String(bytes);
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setTopic(topic);
        kafkaMessage.setBody(JSONObject.parseObject(data));
        return kafkaMessage;
    }


}
