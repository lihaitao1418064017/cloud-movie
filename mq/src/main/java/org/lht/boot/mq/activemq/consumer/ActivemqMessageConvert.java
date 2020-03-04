package org.lht.boot.mq.activemq.consumer;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author LiHaitao
 * @description ActivemqMessageConvert:
 * @date 2020/3/4 16:44
 **/
public class ActivemqMessageConvert implements MessageConverter {


    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return null;
    }
}
