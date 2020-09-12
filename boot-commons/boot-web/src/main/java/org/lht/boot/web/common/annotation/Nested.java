package org.lht.boot.web.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description: es嵌套注解
 * @author: LiHaitao
 * @date: 2020/5/27 17:02
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Inherited
public @interface Nested {
}