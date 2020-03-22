package org.lht.boot.web.domain.entity;

import java.lang.annotation.*;

/**
 * @author LiHaitao
 * @description EsEntity:实体注解
 * @date 2020/1/2 19:22
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EsEntity {

    String index();

    String alias();

    String type();
}
