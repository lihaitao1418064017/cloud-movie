package org.lht.boot.security.resource.web.api.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @description: 用户登录数据传输对象
 */
@Data
public class LoginDTO implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}