package org.lht.boot.mq.producer;

import org.lht.boot.mq.common.entity.KafkaSuccess;
import org.springframework.context.ApplicationEvent;

/**
 * @author LiHaitao
 * @description KafkaSendSuccessEvent:
 * @date 2020/1/16 16:24
 **/
public class KafkaSendSuccessEvent extends ApplicationEvent {


    public KafkaSendSuccessEvent(KafkaSuccess source) {
        super(source);
    }

    @Override
    public KafkaSuccess getSource() {
        return (KafkaSuccess) super.getSource();
    }

}
