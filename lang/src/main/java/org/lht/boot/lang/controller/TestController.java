package org.lht.boot.lang.controller;

import cn.hutool.captcha.LineCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.lang.util.CaptchaUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author LiHaitao
 * @description TestController:
 * @date 2020/3/5 15:47
 **/
@RequestMapping("/")
@RestController
@Slf4j
public class TestController {

    @GetMapping("captcha")
    @ResponseBody
    public String captcha(HttpServletResponse response) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtils.createLineCaptcha(110, 36, 4, 10);
        String code = lineCaptcha.getCode();
        log.info("验证码：{}", code);
        //输出浏览器
        OutputStream out = response.getOutputStream();
        lineCaptcha.write(out);
        out.flush();
        out.close();
        return "nihao";
    }
}
