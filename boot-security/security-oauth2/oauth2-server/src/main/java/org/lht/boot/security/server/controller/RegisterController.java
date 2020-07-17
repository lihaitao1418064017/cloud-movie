package org.lht.boot.security.server.controller;

import cn.hutool.core.util.StrUtil;
import org.lht.boot.cache.redis.RedisUtil;
import org.lht.boot.lang.util.PasswordUtil;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.resource.vo.UserRegisterVO;
import org.lht.boot.lang.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author LiHaitao
 * @description RegisterController:用户注册
 * @date 2020/7/3 14:03
 **/
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RedisUtil<String, String> redisUtil;

    @Autowired
    private UserInfoService userInfoService;


    @PostMapping
    public R register(@Valid @RequestBody UserRegisterVO userRegisterVO
            , HttpServletRequest request
            , HttpServletResponse response) {

        String sessionId = request.getSession().getId();
        String captcha = redisUtil.get(sessionId);
        if (userRegisterVO.getEmail() != null && userRegisterVO.getPhone() != null) {
            return R.error("手机号或邮箱不能为空");
        }
        if (StrUtil.isEmpty(captcha) || !captcha.equals(userRegisterVO.getCaptcha())) {
            return R.error("验证码错误！");
        }
        //校验密码
        if (!PasswordUtil.match(userRegisterVO.getPassword(), PasswordUtil.SIMPLE_ROLE)) {
            return R.error("密码规则不正确!");
        }
        return R.ok(userInfoService.register(userRegisterVO));
    }


    @GetMapping("/captcha")
    public R<String> captcha(@RequestParam(required = false) String phone
            , @RequestParam(required = false) String email
            , HttpServletRequest request
            , HttpServletResponse response) {
        String sessionId = request.getSession().getId();
        String captcha = redisUtil.get(sessionId);
        if (captcha != null) {
            return R.error("验证码已发送");
        }
        //模拟code
        String code = "123456";

        if (email != null) {
            //todo 发送email

            redisUtil.set(sessionId, code, 60);
            return R.ok(code);
        }
        if (phone != null) {
            //todo 发送短信验证码

            redisUtil.set(sessionId, code, 60);

            return R.ok(code);
        }
        return R.error("发送异常");
    }
}
