package org.lht.boot.cache.producer;

import org.springframework.context.ApplicationEvent;

/**
 * @author LiHaitao
 * @description KafkaSendFailEvent:kafka发送失败事件
 * @date 2020/1/16 16:24
 **/
public class KafkaSendFailEvent extends ApplicationEvent {


    public KafkaSendFailEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

}
