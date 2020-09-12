package org.lht.boot.cloud.admin.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.cloud.admin.config.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LiHaitao
 * @description 钉钉获取accessToken工具类
 * @date 2020/7/23 15:49
 **/
@Slf4j
@Component
public class DingTalkAccessTokenUtil {

    @Autowired
    private CommonProperties properties;

    public String getAccessToken() {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(properties.getAppKey());
        request.setAppsecret(properties.getAppSecret());
        request.setHttpMethod("GET");
        OapiGettokenResponse response = null;
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            log.info("获取钉钉服务accessToken失败：{}", e);
            return null;
        }
        return response.getAccessToken();
    }
}
