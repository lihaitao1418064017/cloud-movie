package org.lht.boot.mq.activemq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;

import static org.lht.boot.mq.common.CommonUtil.covertMessage;

/**
 * @author LiHaitao
 * @description ActivemqSender:
 * @date 2020/2/28 10:15
 **/
@Slf4j
public class ActivemqSender<T> extends JmsMessagingTemplate {


    public ActivemqSender(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * 发送到topic
     *
     * @param topicName
     * @param message
     */
    public void sendToTopic(String topicName, T message) {
        super.convertAndSend(new Topic() {
            @Override
            public String getTopicName() throws JMSException {
                return topicName;
            }
        }, covertMessage(message));
    }

    /**
     * 发送到queue
     *
     * @param queueName
     * @param message
     */
    public void sendToQueue(String queueName, T message) {
        super.convertAndSend(new Queue() {
            @Override
            public String getQueueName() throws JMSException {
                return queueName;
            }
        }, covertMessage(message));
    }

}
