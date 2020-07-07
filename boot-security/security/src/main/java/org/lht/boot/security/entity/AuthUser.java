package org.lht.boot.security.entity;

import lombok.Data;

/**
 * @author LiHaitao
 * @description AuthUserVO:
 * @date 2020/5/28 17:27
 **/
@Data
public class AuthUser {

    private Integer id;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;
}
