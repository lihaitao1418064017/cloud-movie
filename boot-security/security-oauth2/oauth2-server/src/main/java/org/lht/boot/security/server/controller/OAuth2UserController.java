package org.lht.boot.security.server.controller;


import org.lht.boot.security.server.domain.OAuth2UserInfoAuthentication;
import org.lht.boot.security.server.service.OAuth2UserService;
import org.lht.boot.web.api.param.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description OAuth2UserController:
 * @date 2020/5/28 15:25
 **/
@RestController
@RequestMapping("/user")
public class OAuth2UserController {

    @Autowired
    private OAuth2UserService oAuth2UserService;

    @GetMapping("/getLoginUser")
    public R<OAuth2UserInfoAuthentication> getLoginUser(@RequestParam("clientId") String clientId
            , @RequestParam("accessToken") String accessToken) {
        return R.ok(oAuth2UserService.getLoginUser(clientId, accessToken));
    }
}
