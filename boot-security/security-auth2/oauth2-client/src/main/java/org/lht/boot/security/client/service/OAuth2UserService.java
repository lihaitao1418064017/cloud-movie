package org.lht.boot.security.client.service;

import org.lht.boot.security.client.entity.OAuth2UserAuthentication;
import org.lht.boot.security.client.service.fallback.OAuth2UserServiceHystrixFallBack;
import org.lht.boot.web.api.param.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LiHaitao
 * @description OAuth2UserService:
 * @date 2020/6/4 16:06
 **/
@FeignClient(value = "boot-oauth2-server", fallback = OAuth2UserServiceHystrixFallBack.class)
public interface OAuth2UserService {

    @GetMapping("/getLoginUser")
    R<OAuth2UserAuthentication> getLoginUserByAccessToken(@RequestParam("clientId") String clientId
            , @RequestParam("clientSecret") String clientSecret
            , @RequestParam("accessToken") String accessToken);

}
