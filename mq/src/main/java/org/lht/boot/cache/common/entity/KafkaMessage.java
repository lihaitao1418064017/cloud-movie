package org.lht.boot.cache.common.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author LiHaitao
 * @description KafkaMessage:通用监听Kafka消息体
 * @date 2020/1/16 18:33
 * @see org.lht.boot.cache.common.config.KafkaConsumerConfig  "basicKafkaListenerContainerFactory"
 **/
@Data
public class KafkaMessage extends JSONObject {

    private String topic;

    private JSONObject body;
}
