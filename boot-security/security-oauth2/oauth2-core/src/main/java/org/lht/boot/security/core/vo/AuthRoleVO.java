package org.lht.boot.security.core.vo;

import lombok.Data;

/**
 * @author LiHaitao
 * @description AuthRoleVO:
 * @date 2020/5/28 17:21
 **/
@Data
public class AuthRoleVO {

    private Integer id;

    private String name;

    private String sign;

    private String description;

}
