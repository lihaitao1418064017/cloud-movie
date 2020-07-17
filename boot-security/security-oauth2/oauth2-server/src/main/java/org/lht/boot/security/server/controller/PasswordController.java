package org.lht.boot.security.server.controller;

import cn.hutool.core.util.StrUtil;
import org.lht.boot.cache.redis.RedisUtil;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.resource.vo.ForgetPasswordVO;
import org.lht.boot.lang.util.R;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.api.param.UpdateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiHaitao
 * @description PasswordController:密码
 * @date 2020/7/6 14:55
 **/
@RestController
@RequestMapping("password")
public class PasswordController {

    @Autowired
    private RedisUtil<String, String> redisUtil;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 忘记密码修改
     *
     * @param passwordVO
     * @param request
     * @return
     */
    @PostMapping("/forget")
    public R forgetPassword(ForgetPasswordVO passwordVO, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String captcha = redisUtil.get(sessionId);
        if (StrUtil.isEmpty(captcha) || !captcha.equals(passwordVO.getCaptcha())) {
            return R.ok("验证码错误");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(passwordVO.getPassword());
        UpdateParam<UserInfo> userInfoUpdateParam = new UpdateParam<>();
        userInfoUpdateParam.addTerm(Term.build("phone", passwordVO.getPhone()));
        return R.ok(userInfoService.patch(userInfoUpdateParam));
    }
}
