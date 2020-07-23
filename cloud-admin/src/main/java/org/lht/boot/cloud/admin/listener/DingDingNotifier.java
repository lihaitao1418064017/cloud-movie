package org.lht.boot.cloud.admin.listener;

import cn.hutool.core.date.DateUtil;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.cloud.admin.config.CommonProperties;
import org.lht.boot.cloud.admin.util.DingDingMessageUtil;
import org.lht.boot.cloud.admin.util.DingTalkAccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author LiHaitao
 * @description 服务状态通知，发送钉钉消息通知
 * @date 2020/7/23 15:55
 **/
@Slf4j
@Component
public class DingDingNotifier extends AbstractStatusChangeNotifier {

    @Autowired
    DingTalkAccessTokenUtil dingTalkAccessTokenUtil;

    @Autowired
    private CommonProperties properties;

    public DingDingNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        String serviceName = instance.getRegistration().getName();
        String serviceUrl = instance.getRegistration().getServiceUrl();
        String status = instance.getStatusInfo().getStatus();
        Map<String, Object> details = instance.getStatusInfo().getDetails();
        StringBuilder str = new StringBuilder();
        str.append("【服务异常】： : " + serviceName + "\n")
                .append("【服务地址】：" + serviceUrl + "\n")
                .append("【状态】：" + status + "\n")
                .append("【异常】：" + details.get("exception"))
                .append("【异常信息】：" + details.get("message"))
                .append("【告警时间】：" + DateUtil.now());

        log.info("服务通知：{}", str);
        return Mono.fromRunnable(() -> {
            try {
                OapiRobotSendResponse oapiRobotSendResponse = DingDingMessageUtil.sendTextMessage(str.toString(), Lists.newArrayList("17809269791"), properties.getAccessToken());
                log.info("钉钉发送消息失败：{}", oapiRobotSendResponse);
            } catch (ApiException e) {
                log.error("钉钉发送消息失败:{}", e);
            }
        });
    }


}
