package org.lht.boot.security.resource.dto;

import lombok.Data;
import org.lht.boot.security.resource.entity.Role;

import java.util.Set;

/**
 * @author LiHaitao
 * @description UserInfoDTO:用户信息dto
 * @date 2020/6/23 15:25
 **/
@Data
public class UserInfoDTO extends BaseCrudDTO {

    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证
     */
    private String identify;

    /**
     * 性别：0：男 ，1：女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色信息
     */
    private Set<Role> roles;
}
