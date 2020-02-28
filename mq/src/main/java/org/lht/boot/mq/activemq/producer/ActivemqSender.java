package org.lht.boot.mq.activemq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.lht.boot.mq.activemq.config.ActivemqProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.io.Serializable;

/**
 * @author LiHaitao
 * @description ActivemqSender:
 * @date 2020/2/28 10:15
 **/
@Slf4j
public class ActivemqSender {

    @Autowired
    private ActivemqProperties activemqProperties;

    private volatile ConnectionFactory factory;
    private volatile Connection connection = null;
    private volatile Session session;


    public ActivemqSender() {
        try {
            this.factory = new ActiveMQConnectionFactory(activemqProperties.getUsername(), activemqProperties.getPassword(),
                    activemqProperties.getUrl());
            // 获取连接实例
            this.connection = factory.createConnection();
            this.connection.start();
            // 创建接收或发送的线程实例（创建session的时候定义是否要启用事务，且事务类型是Auto_ACKNOWLEDGE也就是消费者成功在Listern中获得消息返回时，会话自动确定用户收到消息）
            this.session = connection.createSession(activemqProperties.getIsAcknowledge(), activemqProperties.getAcknowledge());
        } catch (JMSException e) {
            log.info("JMSException：{}", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送文本消息
     *
     * @param queue
     * @param textMessage
     */
    private void sendQueue(String queue, String textMessage) {
        // 消息发送目标地址
        //        Destination destination = null;
        // 消息创建者
        MessageProducer messageProducer = null;
        try {
            // 写入消息队列
            Destination destination = new ActiveMQQueue(queue);
            // 创建队列（返回一个消息目的地）
            //            destination = session.createQueue(queue);
            messageProducer = this.session.createProducer(destination);
            // 创建TextMessage消息实体
            TextMessage message = this.session.createTextMessage(textMessage);
            messageProducer.send(message);
        } catch (JMSException e) {
            log.info("连接失败");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 发送文本消息
     *
     * @param topic
     * @param textMessage
     */
    private void sendTopic(String topic, String textMessage) {
        // 消息发送目标地址
        Destination destination = null;
        // 消息创建者
        MessageProducer messageProducer = null;
        try {
            // 创建队列（返回一个消息目的地）
            destination = session.createTopic(topic);
            messageProducer = this.session.createProducer(destination);
            // 创建TextMessage消息实体
            TextMessage message = this.session.createTextMessage(textMessage);
            messageProducer.send(message);
            messageProducer.close();
        } catch (JMSException e) {
            log.info("连接失败");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 发送文本消息
     *
     * @param queue
     * @param textMessage
     */
    private void sendQueue(String queue, Serializable textMessage) {
        // 消息发送目标地址
        Destination destination = null;
        // 消息创建者
        MessageProducer messageProducer = null;
        try {
            // 创建队列（返回一个消息目的地）
            destination = session.createQueue(queue);
            messageProducer = this.session.createProducer(destination);
            // 创建TextMessage消息实体
            ObjectMessage message = this.session.createObjectMessage(textMessage);
            messageProducer.send(message);
            this.session.commit();
        } catch (JMSException e) {
            log.info("发送失败");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 发送文本消息
     *
     * @param topic
     * @param textMessage
     */
    private void sendTopic(String topic, Serializable textMessage) {
        // 消息发送目标地址
        Destination destination = null;
        // 消息创建者
        MessageProducer messageProducer = null;
        try {
            // 创建队列（返回一个消息目的地）
            destination = session.createTopic(topic);
            messageProducer = this.session.createProducer(destination);
            // 创建TextMessage消息实体
            ObjectMessage message = this.session.createObjectMessage(textMessage);
            messageProducer.send(message);
            this.session.commit();
        } catch (JMSException e) {
            log.info("发送失败");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
