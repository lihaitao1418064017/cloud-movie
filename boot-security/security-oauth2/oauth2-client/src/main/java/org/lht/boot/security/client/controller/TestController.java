package org.lht.boot.security.client.controller;

import org.lht.boot.security.client.service.OAuth2UserService;
import org.lht.boot.web.api.param.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description TesController:权限测试controller
 * @date 2020/6/2 20:04
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private OAuth2UserService oAuth2UserService;

    @GetMapping("hello")
    public R hello() {
        return R.ok();
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasPermission('/user','user')")
    public R printUser() {
        return R.ok();
    }
}
