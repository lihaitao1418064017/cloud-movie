package org.lht.boot.security.resource.vo;

import lombok.Data;

/**
 * @author LiHaitao
 * @description ForgetPasswordVO:忘记密码
 * @date 2020/7/6 14:11
 **/
@Data
public class ForgetPasswordVO {

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String phone;

    /**
     * 验证码
     */
    private String captcha;
}
