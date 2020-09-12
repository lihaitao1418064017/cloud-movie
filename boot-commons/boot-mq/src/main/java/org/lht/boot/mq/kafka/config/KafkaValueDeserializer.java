package org.lht.boot.mq.kafka.config;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Deserializer;
import org.lht.boot.mq.kafka.entity.KafkaMessage;

/**
 * @author LiHaitao
 * @description KafkaValueDeserializer:kafka值的序列化
 * @date 2020/1/16 16:59
 **/
public class KafkaValueDeserializer implements Deserializer<KafkaMessage> {


    @Override
    public KafkaMessage deserialize(String topic, byte[] bytes) {
        String data = new String(bytes);
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setTopic(topic);
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            kafkaMessage.setData(jsonObject);
        } catch (JSONException e) {
            kafkaMessage.setData(data);
            return kafkaMessage;
        }
        return kafkaMessage;
    }


}
