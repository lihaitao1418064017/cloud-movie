package org.hhy.xxl.crud.annotation;

import java.lang.annotation.*;

/**
 *
 * @author LiuHao
 * @date 2020/9/17 15:24
 * @description 数据库表相关配置注解
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {
    String value();
}
