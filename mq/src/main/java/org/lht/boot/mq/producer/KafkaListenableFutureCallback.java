package org.lht.boot.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.lht.boot.mq.common.entity.KafkaSuccess;
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
            applicationEventPublisher.publishEvent(throwable);
            throw throwable;
        } catch (Throwable throwable1) {
            throw new KafkaException("发送失败");
        }
    }

    @Override
    public void onSuccess(@Nullable T t) {
        //do something
        log.info("do something");
        SendResult sendResult = (SendResult) t;
        Object value = sendResult.getProducerRecord().value();
        String topic = sendResult.getProducerRecord().topic();
        KafkaSuccess kafkaSuccess = new KafkaSuccess();
        kafkaSuccess.setBody(value);
        kafkaSuccess.setTopic(topic);
        //发送成功事件
        applicationEventPublisher.publishEvent(new KafkaSendSuccessEvent(kafkaSuccess));
    }
}
