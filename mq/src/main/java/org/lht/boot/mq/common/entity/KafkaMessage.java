package org.lht.boot.mq.common.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author LiHaitao
 * @description KafkaMessage:
 * @date 2020/1/16 18:33
 **/
@Data
public class KafkaMessage extends JSONObject {

    private String topic;

    private JSONObject body;
}
