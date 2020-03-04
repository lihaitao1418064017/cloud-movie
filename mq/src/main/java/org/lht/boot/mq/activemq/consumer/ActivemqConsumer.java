package org.lht.boot.mq.activemq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.lht.boot.mq.common.User;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author LiHaitao
 * @description ActivemqConsumer:
 * @date 2020/2/28 15:11
 **/
@Component
@Slf4j
public class ActivemqConsumer {


    @JmsListener(destination = "topicUser", containerFactory = "queueListener")
    public void onMessage(String message) {
        log.info("消息对象{}", message);
    }

    @JmsListener(destination = "topicUser", containerFactory = "topicListener")
    public void onMessageTopic(User message) {
        log.info("消息：{}", message);
    }
}
