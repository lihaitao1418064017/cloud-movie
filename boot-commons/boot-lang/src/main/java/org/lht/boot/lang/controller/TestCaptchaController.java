package org.lht.boot.lang.controller;

import cn.hutool.captcha.LineCaptcha;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.lang.util.CaptchaUtils;
import org.lht.boot.lang.util.IpUtils;
import org.lht.boot.lang.util.ServletUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static org.lht.boot.lang.util.IpUtils.getIpAddr;

/**
 * @author LiHaitao
 * @description TestController:
 * @date 2020/3/5 15:47
 **/
@RequestMapping("/")
@RestController
@Slf4j
@Qualifier("testController")
public class TestCaptchaController {

    @GetMapping("captcha")
    @ResponseBody
    public String captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtils.createLineCaptcha(110, 36, 4, 10);
        String code = lineCaptcha.getCode();
        log.info("验证码：{}", code);
        String id = request.getSession().getId();
        log.info("sessionId:{}", id);
        request.getSession().setAttribute(request.getSession().getId(), code);
        //输出浏览器
        OutputStream out = response.getOutputStream();
        lineCaptcha.write(out);
        out.flush();
        out.close();
        return "nihao";
    }


    @PostMapping(value = "test", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject check(@RequestBody Vo jsonObject) throws IOException {
        log.info("jsonObject{}", jsonObject.toJSONString());
        return jsonObject;
    }


    @GetMapping("/ipv4")
    public String getIpv4(){
        String ipAddr = getIpAddr(ServletUtil.getServletRequest());
        log.info("ip :{}",ipAddr);

        return IpUtils.getV4IP();
    }

}
