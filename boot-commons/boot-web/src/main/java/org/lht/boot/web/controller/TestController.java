package org.lht.boot.web.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.lang.controller.Vo;
import org.lht.boot.web.common.annotation.Limit;
import org.lht.boot.web.domain.LimitType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author LiHaitao
 * @description TestController:测试controller
 * @date 2019/12/13 16:00
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping
    public String test() {

        return "success";
    }

    @PostMapping("/api")
    @Limit(name = "test接口限流", key = "test", prefix = "api_", period = 2, count = 1, limitType = LimitType.IP)
    public JSONObject check(@RequestBody Vo jsonObject) throws IOException {
        log.info("jsonObject{}", jsonObject.toJSONString());
        return jsonObject;
    }
}
