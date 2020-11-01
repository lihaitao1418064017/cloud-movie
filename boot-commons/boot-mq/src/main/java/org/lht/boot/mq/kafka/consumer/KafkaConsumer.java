package org.lht.boot.mq.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author LiHaitao
 * @description KafkaConsumer:
 * @date 2020/1/16 14:51
 **/
//@Component
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = {"test_order"})
    public void listener(String message) {
        log.info("message:{}", message);
        System.err.println("333333333333333333333333");

    }

    //    @KafkaListener(topics = "order")
    //    public void listener1(String message) {
    //        log.info("message1:{}", message);
    //        System.err.println("222222222222222222222");
    //
    //    }
    //
    //    @KafkaListener(topics = {"order"})
    //    public void listener2(String message) {
    //        log.info("message2:{}", message);
    //        System.err.println("111111111111111111111111111");
    //    }
}
