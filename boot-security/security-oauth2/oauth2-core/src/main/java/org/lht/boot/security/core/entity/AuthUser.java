package org.lht.boot.security.core.entity;

import lombok.Data;

/**
 * @author LiHaitao
 * @description AuthUser:
 * @date 2020/5/28 17:27
 **/
@Data
public class AuthUser {

    private Integer id;

    private String username;

    private String password;

    private boolean accountNonExpired = false;

    private boolean accountNonLocked = false;

    private boolean credentialsNonExpired = false;

    private boolean enabled = true;
}
