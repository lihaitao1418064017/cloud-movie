package org.lht.boot.security.server.listener;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.lang.util.BeanUtils;
import org.lht.boot.security.server.domain.entity.AccessLog;
import org.lht.boot.security.server.service.AccessLogService;
import org.lht.boot.web.common.event.AccessLoggerEvent;
import org.lht.boot.web.domain.entity.AccessLoggerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiHaitao
 * @description 日志存储
 * @date 2020/7/13 14:14
 **/
@Slf4j
@Component
public class AccessLogListener implements ApplicationListener<AccessLoggerEvent> {


    @Autowired
    private AccessLogService accessLogService;

    @Override
    public void onApplicationEvent(AccessLoggerEvent accessLoggerEvent) {
        AccessLoggerInfo source = accessLoggerEvent.getSource();
        AccessLog accessLog = new AccessLog();
        BeanUtils.copyProperties(source, accessLog);
        if (source.getException() != null) {
            accessLog.setException(source.getException().getMessage());
        }
        Map<String, Object> parameters = source.getParameters();
        Map<String, String> ps = new HashMap<String, String>();
        parameters.forEach((k, v) -> {
            ps.put(k, v.toString());
        });
        accessLog.setParams(JSONObject.toJSONString(ps));
        accessLogService.insert(accessLog);
    }
}
