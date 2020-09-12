package org.lht.boot.web.common.annotation;

import java.lang.annotation.*;

/**
 * @author LiHaitao
 * @description 访问日志
 * @date 2020/7/13 11:31
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AccessLogger {

    /**
     * @return 对类或方法的简单说明
     */
    String value();

    /**
     * @return 对类或方法的详细描述
     */
    String[] describe() default "";

    /**
     * @return 是否取消日志记录, 如果不想记录某些方法或者类, 设置为true即可
     */
    boolean ignore() default false;
}
