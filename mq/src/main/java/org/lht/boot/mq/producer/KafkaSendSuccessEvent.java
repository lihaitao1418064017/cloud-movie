package org.lht.boot.mq.producer;

import org.springframework.context.ApplicationEvent;

/**
 * @author LiHaitao
 * @description KafkaSendSuccessEvent:
 * @date 2020/1/16 16:24
 **/
public class KafkaSendSuccessEvent extends ApplicationEvent {


    public KafkaSendSuccessEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

}
