package org.lht.boot.security.client.controller;

import org.lht.boot.security.client.entity.AuthUserDetails;
import org.lht.boot.security.client.service.OAuth2AuthenticationService;
import org.lht.boot.web.api.param.R;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description OAuth2AuthorityController:用户信息获取
 * @date 2020/7/1 10:59
 **/
@RequestMapping("/authority")
@RestController
public class OAuth2AuthorityController {


    @GetMapping("/me")
    public R me() {
        PreAuthenticatedAuthenticationToken authentication = (PreAuthenticatedAuthenticationToken) OAuth2AuthenticationService.getAuthentication();
        AuthUserDetails details = (AuthUserDetails) authentication.getPrincipal();
        return R.ok(details.getAuthentication());
    }
}
