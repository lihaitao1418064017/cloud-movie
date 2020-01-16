package org.lht.boot.web.api.param;

/**
 * @author LiHaitao
 * @description DeleteParam:
 * @date 2020/1/8 14:59
 **/
public class DeleteParam extends Param {


    /**
     * 创建一个空的查询参数实体,该实体无任何参数.
     *
     * @return 无条件的参数实体
     */
    public static DeleteParam empty() {
        return new DeleteParam();
    }

    /**
     * 创建一个空的查询参数实体,该实体无任何参数.
     *
     * @return 无条件的参数实体
     */
    public static DeleteParam build() {
        return new DeleteParam();
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

