package org.hhy.xxl.crud.annotation;

import java.lang.annotation.*;

/**
 *
 * @author LiuHao
 * @date 2020/9/19 12:40
 * @description 标识主键字段,要求必须只有一个主键
*/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ID {

}
