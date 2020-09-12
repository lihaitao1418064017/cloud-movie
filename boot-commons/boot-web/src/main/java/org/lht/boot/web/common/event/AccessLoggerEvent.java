package org.lht.boot.web.common.event;

import org.lht.boot.web.domain.entity.AccessLoggerInfo;
import org.springframework.context.ApplicationEvent;

/**
 * @description: 日志事件
 * @author: LiHaitao
 * @date: 2020/7/13 13:08
 */
public class AccessLoggerEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AccessLoggerEvent(AccessLoggerInfo source) {
        super(source);
    }

    @Override
    public AccessLoggerInfo getSource() {
        return (AccessLoggerInfo) super.getSource();
    }
}
