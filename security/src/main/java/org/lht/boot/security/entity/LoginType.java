package org.lht.boot.security.entity;

import io.swagger.annotations.ApiModel;

/**
 * @author LiHaitao
 * @description
 * @date 2020/3/25 19:50
 **/
@ApiModel("登陆类型枚举")
public enum LoginType {
    NORMAL, SMS, SOCIAL;
}
