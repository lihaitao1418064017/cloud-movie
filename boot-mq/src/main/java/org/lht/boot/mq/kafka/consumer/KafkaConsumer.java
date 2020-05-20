package org.lht.boot.mq.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.lht.boot.mq.kafka.entity.KafkaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author LiHaitao
 * @description KafkaConsumer:
 * @date 2020/1/16 14:51
 **/
@Component
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = {"${cvf.viid.kafka.topic:Notify}"}, containerFactory = "basicKafkaListenerContainerFactory")
    public void listener(KafkaMessage<String> message) {
        log.info("message:{}", message);
    }
}
