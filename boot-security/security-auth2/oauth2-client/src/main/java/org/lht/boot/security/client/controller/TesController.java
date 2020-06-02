package org.lht.boot.security.client.controller;

import org.lht.boot.web.api.param.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description TesController:
 * @date 2020/6/2 20:04
 **/
@RestController
@RequestMapping("/test")
public class TesController {


    @GetMapping("hello")
    public R hello() {
        return R.ok();
    }
}
