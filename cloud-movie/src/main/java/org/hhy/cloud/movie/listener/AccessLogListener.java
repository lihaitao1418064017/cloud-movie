package org.hhy.cloud.movie.listener;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.hhy.cloud.movie.entity.AccessLog;
import org.hhy.cloud.movie.service.AccessLogService;
import org.lht.boot.lang.util.BeanUtils;

import org.lht.boot.mq.kafka.producer.KafkaSender;
import org.lht.boot.web.common.event.AccessLoggerEvent;
import org.lht.boot.web.domain.entity.AccessLoggerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.hhy.cloud.movie.common.constant.CommonConstant.KafkaTopics.ACCESSLOG_TOPIC;

/**
 * @author LiHaitao
 * @description 日志存储
 * @date 2020/9/13 14:14
 **/
@Slf4j
@Component
public class AccessLogListener implements ApplicationListener<AccessLoggerEvent> {


    @Autowired
    private AccessLogService accessLogService;

    @Autowired
    public KafkaSender<String,AccessLoggerInfo> kafkaSender;

    @Async
    @Override
    public void onApplicationEvent(AccessLoggerEvent accessLoggerEvent) {
        AccessLoggerInfo source = accessLoggerEvent.getSource();
        kafkaSender.sendByJsonStr(ACCESSLOG_TOPIC,source);
    }

    @KafkaListener(topics = {ACCESSLOG_TOPIC})
    public void listener(String message){
        AccessLog accessLog;
        try {
            JSONObject obj = JSONObject.parseObject(message);
            JSONObject data = obj.getJSONObject("data");
            AccessLoggerInfo accessLoggerInfo = JSONObject.toJavaObject(data, AccessLoggerInfo.class);
            accessLog=this.createAccessLog(accessLoggerInfo);
        }catch (JSONException e){
            log.error("json parse exception:{}",e.getMessage());
            return;
        }
        accessLogService.insert(accessLog);
    }

    private AccessLog createAccessLog(AccessLoggerInfo source) {
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
        return accessLog;
    }
}
