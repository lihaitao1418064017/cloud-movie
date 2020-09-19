package org.hhy.xxl.crud.annotation;

import java.lang.annotation.*;

/**
 *
 * @author LiuHao
 * @date 2020/9/17 15:32
 * @description 数据表名相关配置
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TableField {
    /**
     * 字段名
     * @return
     */
    String value();

    /**
     * 是否为数据库表字段（ 默认 true 存在，false 不存在 ）
     * @return
     */
    boolean exist() default true;
}
