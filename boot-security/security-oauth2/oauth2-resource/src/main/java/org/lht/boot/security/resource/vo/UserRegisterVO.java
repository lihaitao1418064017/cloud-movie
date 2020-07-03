package org.lht.boot.security.resource.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author LiHaitao
 * @description UserRegisterVO:用户注册信息VO
 * @date 2020/7/3 14:05
 **/
@Data
public class UserRegisterVO {

    private String phone;

    private String email;

    @NotNull
    private String username;

    private String password;

    @NotNull
    private String captcha;


}
