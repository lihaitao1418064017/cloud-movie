package org.lht.boot.web.api.param;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description Term:
 * @date 2020/1/8 15:20
 **/
public class Term implements Cloneable, Serializable {


    /**
     * 字段
     */
    private String column;

    /**
     * 值
     */
    private Object value;

    /**
     * 链接类型
     */
    private Type type = Type.and;

    /**
     * 条件类型
     */
    private TermEnum termType = TermEnum.eq;


    public String getColumn() {
        return column;
    }

    public Term setColumn(String column) {
        if (column == null) {
            return this;
        }
        if (column.contains("$")) {
            String tmp[] = column.split("[$]");
            setTermType(TermEnum.parse(tmp[1]));
            column = tmp[0];
        }
        this.column = column;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Term setValue(Object value) {
        this.value = value;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Term setType(Type type) {
        this.type = type;
        return this;
    }

    public TermEnum getTermType() {
        return termType;
    }

    public Term setTermType(TermEnum termType) {
        this.termType = termType;
        return this;
    }


    public enum Type {
        or, and;
    }


    public static Term build(String column, Object value) {
        return build(column, TermEnum.eq, value);
    }

    public static Term build(String column, TermEnum operator) {
        return build(column, operator, null);
    }

    public static Term build(String column, TermEnum operator, Object value) {
        Term term = new Term();
        term.column = column;
        term.value = value;
        term.termType = operator;
        return term;
    }


}
