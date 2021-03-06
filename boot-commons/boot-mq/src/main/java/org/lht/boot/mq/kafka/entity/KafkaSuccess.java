package org.lht.boot.mq.kafka.entity;

import lombok.Data;

/**
 * @author LiHaitao
 * @description KafkaSuccess:发送成功消息体
 * @date 2020/1/16 19:19
 **/
@Data
public class KafkaSuccess {

    private String topic;

    private Object body;
}
