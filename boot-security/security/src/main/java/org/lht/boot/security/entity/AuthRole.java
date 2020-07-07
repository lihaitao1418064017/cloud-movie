package org.lht.boot.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author LiHaitao
 * @description AuthRoleVO:
 * @date 2020/5/28 17:21
 **/
@Data
public class AuthRole implements GrantedAuthority {

    private Integer id;

    private String name;

    private String sign;

    private String description;

    @Override
    public String getAuthority() {
        return sign;
    }
}
