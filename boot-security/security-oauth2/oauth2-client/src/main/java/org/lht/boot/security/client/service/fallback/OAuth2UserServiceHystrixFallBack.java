package org.lht.boot.security.client.service.fallback;

import org.lht.boot.security.client.service.OAuth2UserService;
import org.lht.boot.security.core.entity.OAuth2UserAuthentication;
import org.lht.boot.lang.util.R;
import org.springframework.stereotype.Component;

/**
 * @author LiHaitao
 * @description OAuth2UserServiceHystrixFallBack:熔断
 * @date 2020/6/4 19:34R
 **/
@Component
public class OAuth2UserServiceHystrixFallBack implements OAuth2UserService {

    @Override
    public R<OAuth2UserAuthentication> getLoginUserByAccessToken(String clientId, String accessToken) {
        return R.error("Gets a logon user error");
    }
}
