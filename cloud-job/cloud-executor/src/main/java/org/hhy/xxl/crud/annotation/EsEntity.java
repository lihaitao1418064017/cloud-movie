package org.hhy.xxl.crud.annotation;

import java.lang.annotation.*;

/**
 *
 * @author LiuHao
 * @date 2020/9/17 15:28
 * @description elastic search curd相关配置 注解
*/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EsEntity {
    String index();

    String alias();

    String type();
}

