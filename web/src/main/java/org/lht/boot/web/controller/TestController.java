package org.lht.boot.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description TestController:测试controller
 * @date 2019/12/13 16:00
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {

        return "success";
    }
}
