package org.lht.boot.web.api.param;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author LiHaitao
 * @description QueryParam:
 * @date 2019/12/13 16:49
 **/
@Data
public class QueryParam extends Param {

    private static final long serialVersionUID = 7941767360194797891L;

    /**
     * 是否进行分页，默认为true
     */
    private boolean paging = true;

    /**
     * 第几页 从0开始
     */
    private int pageNo = 0;

    /**
     * 每页显示记录条数
     */
    private int pageSize = 25;


    /**
     * 排序字段
     */
    private List<Sort> sorts = new LinkedList<>();

    private Long total;


    public <Q extends QueryParam> Q doPaging(int pageNo) {
        this.pageNo = pageNo;
        this.paging = true;
        return (Q) this;
    }

    public <Q extends QueryParam> Q doPaging(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.paging = true;
        return (Q) this;
    }

    public Param sort(List<Sort> sorts) {
        this.sorts = sorts;
        return this;
    }


    /**
     * 创建一个空的查询参数实体,该实体无任何参数.
     *
     * @return 无条件的参数实体
     */
    public static QueryParam empty() {
        return new QueryParam();
    }

    /**
     * 创建一个空的查询参数实体,该实体无任何参数.
     *
     * @return 无条件的参数实体
     */
    public static QueryParam build() {
        return new QueryParam();
    }

    /**
     * 创建一个含有单个条件的参数实体,条件默认为is
     *
     * @param field 参数名称
     * @param value 参数值
     * @return 单个条件的参数实体
     * @see QueryParam#build(String, Object)
     */
    @Deprecated
    public static Param single(String field, Object value) {
        return build(field, value);
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
