package org.lht.boot.web.api.param;

import lombok.Data;

/**
 * @author LiHaitao
 * @description UpdateParam:
 * @date 2020/1/8 14:55
 **/
@Data
public class UpdateParam<T> extends Param {

    private T data;


    /**
     * 创建一个空的查询参数实体,该实体无任何参数.
     *
     * @return 无条件的参数实体
     */
    public static UpdateParam empty() {
        return new UpdateParam();
    }

    /**
     * 创建一个空的查询参数实体,该实体无任何参数.
     *
     * @return 无条件的参数实体
     */
    public static UpdateParam build() {
        return new UpdateParam();
    }


    public static Param build(String field, Object value) {
        return empty().where(field, value);
    }


    public static Param build(String field, TermEnum operator, Object value) {
        return empty().where(field, operator, value);
    }

    public static Param build(Term term) {
        return empty().where(term);
    }


}
