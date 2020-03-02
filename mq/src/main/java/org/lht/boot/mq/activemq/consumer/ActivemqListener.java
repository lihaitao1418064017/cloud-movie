package org.lht.boot.mq.activemq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author LiHaitao
 * @description ActivemqListener:
 * @date 2020/3/2 10:56
 **/
@Component
@Slf4j
public class ActivemqListener {

    @JmsListener(destination = "test")
    public void onMessage(String message) {
        log.info("消息对象{}", message);
    }
}
