package org.lht.boot.security.core.entity;

import lombok.Data;

/**
 * @author LiHaitao
 * @description AuthRole:
 * @date 2020/5/28 17:21
 **/
@Data
public class AuthRole {

    private Integer id;

    private String name;

    private String sign;

    private String description;

}
