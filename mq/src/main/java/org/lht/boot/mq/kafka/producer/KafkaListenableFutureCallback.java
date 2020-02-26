package org.lht.boot.mq.kafka.producer;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.lht.boot.mq.kafka.entity.KafkaSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author LiHaitao
 * @description KafkaListenableFuture:kafka发送消息成功失败回调函数
 * @date 2020/1/16 15:29
 **/
@Slf4j
@Component
public class KafkaListenableFutureCallback<T> implements ListenableFutureCallback<T> {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void onFailure(Throwable throwable) {
        try {
            throw throwable;
        } catch (Throwable throwable1) {
            throw new KafkaException("发送失败");
        } finally {
            applicationEventPublisher.publishEvent(throwable);
        }
    }

    @Override
    public void onSuccess(@Nullable T t) {
        log.info("响应成功，消息体为null:{}", t);
        KafkaSuccess kafkaSuccess = new KafkaSuccess();
        try {
            if (ObjectUtil.isNull(t)) {
            } else {
                SendResult sendResult = (SendResult) t;
                if (ObjectUtil.isNotNull(sendResult)) {
                    Object value = sendResult.getProducerRecord().value();
                    String topic = sendResult.getProducerRecord().topic();
                    kafkaSuccess.setBody(value);
                    kafkaSuccess.setTopic(topic);
                }
            }
        } catch (Exception e) {
            throw new KafkaException("发送成功，回调失败:{}");
        } finally {
            //发送成功事件
            applicationEventPublisher.publishEvent(new KafkaSendSuccessEvent(kafkaSuccess));
        }


    }
}
