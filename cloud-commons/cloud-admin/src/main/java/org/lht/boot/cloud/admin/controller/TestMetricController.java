package org.lht.boot.cloud.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/9/5 下午3:49
 **/
@RequestMapping("/metric")
@RestController
public class TestMetricController {


    @GetMapping("/")
    public String ok(){
        return "ok";
    }
}
