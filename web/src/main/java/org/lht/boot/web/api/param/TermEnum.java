package org.lht.boot.web.api.param;


import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author LiHaitao
 * @description TermEnum:
 * @date 2020/1/8 15:21
 **/
public enum TermEnum {

    /**
     * ==
     *
     * @since 1.0
     */
    eq("eq"),

    /**
     * !=
     *
     * @since 1.0
     */
    not("not"),

    /**
     * like
     *
     * @since 1.0
     */
    like("like"),

    /**
     * not like
     *
     * @since 1.0
     */
    nlike("nlike"),

    /**
     * >
     *
     * @since 1.0
     */
    gt("gt"),

    /**
     * <
     *
     * @since 1.0
     */
    lt("lt"),

    /**
     * >=
     *
     * @since 1.0
     */
    gte("gte"),

    /**
     * <=
     *
     * @since 1.0
     */
    lte("lte"),

    /**
     * in
     *
     * @since 1.0
     */
    in("in"),

    /**
     * notin
     *
     * @since 1.0
     */
    nin("nin"),

    /**
     * =''
     *
     * @since 1.0
     */
    empty("empty"),

    /**
     * !=''
     *
     * @since 1.0
     */
    nempty("nempty"),

    /**
     * is null
     *
     * @since 1.0
     */
    isnull("isnull"),

    /**
     * not null
     *
     * @since 1.0
     */
    notnull("notnull"),

    /**
     * between
     *
     * @since 1.0
     */
    btw("btw"),

    /**
     * not between
     *
     * @since 1.0
     */
    nbtw("nbtw"),

    /**
     * GeoShape 关系
     *
     * @see org.elasticsearch.common.geo.ShapeRelation
     * @since 3.0.6
     */
    intersects("intersects"),

    /**
     * GeoShape 关系
     *
     * @see org.elasticsearch.common.geo.ShapeRelation
     * @since 3.0.6
     */
    disjoint("disjoint"),

    /**
     * GeoShape 关系
     *
     * @see org.elasticsearch.common.geo.ShapeRelation
     * @since 3.0.6
     */
    within("within"),

    /**
     * GeoShape 关系
     *
     * @see org.elasticsearch.common.geo.ShapeRelation
     * @since 3.0.6
     */
    contains("contains");

    TermEnum(String value) {
        this.value = value;
    }


    private static final Map<String, TermEnum> CACHE = Maps.newHashMap();

    static {
        if (values() != null) {
            for (TermEnum operator : values()) {
                CACHE.put(operator.getValue(), operator);
            }
        }
    }

    private String value;


    public static TermEnum parse(String operation) {
        return CACHE.get(operation);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
