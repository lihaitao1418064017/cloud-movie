package org.lht.boot.security.client.service;

import org.lht.boot.security.client.entity.OAuth2UserAuthentication;
import org.lht.boot.web.api.param.R;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description OAuth2ServerService: feignclient  获取服务端资源接口
 * @date 2020/5/28 16:40
 **/
//@FeignClient(name = "resource", url = "${commons.oauth2.client.serverInUri}")
@Service
public class OAuth2ServerService {


    public R<OAuth2UserAuthentication> getLoginUserByAccessToken(String clientId, String clientSecret, String accessToken) {
        return null;
    }
}
