package org.lht.boot.security.client.controller;

import org.lht.boot.web.api.param.R;
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
public class TesController {


    @GetMapping("hello")
    public R hello() {
        return R.ok();
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasPermission('/user','user')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }
}
