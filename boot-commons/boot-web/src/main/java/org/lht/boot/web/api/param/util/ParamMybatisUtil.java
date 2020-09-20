package org.lht.boot.web.api.param.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.lang.util.ClassUtil;
import org.lht.boot.web.api.param.*;
import org.lht.boot.web.common.exception.NotSupportedException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Description: mybatis参数工具类
 *
 * @Author lht
 * @Date 2020/1/8 8:47 PM
 **/
@Slf4j
public class ParamMybatisUtil {

    /**
     * 转换为QueryWrapper
     *
     * @param queryParam
     * @param <E>
     * @return
     */
    public static <E> QueryWrapper<E> toQueryWrapper(Param queryParam) {
        queryParam.addTerm(Term.build("",""))
                .addTerm(Term.build("",""))
                .or("","");

        List<Term> terms = queryParam.getTerms();
        QueryWrapper<E> queryWrapper = new QueryWrapper<E>();
        buildFieldsWrapper(queryParam, queryWrapper);
        buildSort(queryParam, queryWrapper);
        terms.forEach((Term term) -> {
            /**
             * todo：
             * QueryWrapper只支持数据库字段写法（数据库字段为下划线），不支持实体类字段名写法（驼峰写法）
             * 这里临时做一层转化，将驼峰转化成下划线。
             */
            String column = term.getColumn();
            term.setColumn(StrUtil.toUnderlineCase(column));
            termToQueryWrapper(term, queryWrapper);
        });
        return queryWrapper;
    }


    public static  PagerResult buildPage(QueryParam queryParam) {
        PagerResult page = new PagerResult();
        page.setCurrent(queryParam.getPageNo());
        page.setSize(queryParam.getPageSize());
        return page;
    }

    private static <E> void buildFieldsWrapper(Param queryParam, QueryWrapper<E> queryWrapper) {
        Set<String> excludes = queryParam.getExcludes();
        Class<E> genericType = (Class<E>) ClassUtil.getGenericType(queryParam.getClass(), 0);
        if (Object.class == genericType || genericType == null) {
            log.info("QueryParam not exists Generic", genericType);
            return;
        }
        excludes.forEach(exclude -> {
            queryWrapper.excludeColumns(genericType, exclude);
        });
    }


    /**
     * 构建排序
     *
     * @param param
     * @param queryWrapper
     * @param <E>
     */
    public static <E> void buildSort(Param param, QueryWrapper<E> queryWrapper) {
        if (param instanceof QueryParam) {
            QueryParam queryParam = (QueryParam) param;
            List<Sort> sorts = queryParam.getSorts();
            sorts.forEach(sort -> {
                SortEnum order = sort.getOrder();
                switch (order) {
                    case ASC:
                        queryWrapper.orderByAsc(sort.getName());
                        break;
                    case DESC:
                        queryWrapper.orderByDesc(sort.getName());
                        break;
                    default:
                        throw new NotSupportedException(String.format("operator[%s] not supported in sort", order));
                }
            });
        }
    }


    @SuppressWarnings("all")
    public static <E> void termToQueryWrapper(Term term, QueryWrapper<E> queryWrapper) {
        String field = term.getColumn();
        Object value = term.getValue();
        switch (term.getType()) {
            case or:
                or(term, queryWrapper, field, value);
                break;
            case and:
                and(term, queryWrapper, field, value);
                break;
            default:
                throw new NotSupportedException(String.format("operator[%s] not supported in mybatis and Type.", field));


        }
    }

    private static <E> void and(Term term, QueryWrapper<E> queryWrapper, String field, Object value) {
        switch (term.getTermType()) {
            case eq:
                queryWrapper.and(obj -> obj.eq(field, value));
                break;
            case in:
                queryWrapper.and(obj -> obj.in(field, (Collection) value));
                break;
            case like:
                queryWrapper.and(obj -> obj.like(field, value));
                break;
            case isnull:
                queryWrapper.and(obj -> obj.isNotNull(field));
                break;
            case not:
                queryWrapper.and(obj -> obj.ne(field, value));
                break;
            case lt:
                queryWrapper.and(obj -> obj.lt(field, value));
                break;
            case gt:
                queryWrapper.and(obj -> obj.gt(field, value));
                break;
            case gte:
                queryWrapper.and(obj -> obj.ge(field, value));
                break;
            case lte:
                queryWrapper.and(obj -> obj.le(field, value));
                break;
            case nin:
                queryWrapper.and(obj -> obj.notIn(field, value));
                break;
            case nlike:
                queryWrapper.and(obj -> obj.notLike(field, value));
                break;
            case notnull:
                queryWrapper.and(obj -> obj.isNotNull(field));
                break;
            default:
                throw new NotSupportedException(String.format("operator[%s] not supported in mybatis and TermType.", field));

        }
    }

    private static <E> void or(Term term, QueryWrapper<E> queryWrapper, String field, Object value) {
        switch (term.getTermType()) {
            case eq:
                queryWrapper.or(obj -> obj.eq(field, value));
                break;
            case in:
                queryWrapper.or(obj -> obj.in(field, value));
                break;
            case like:
                queryWrapper.or(obj -> obj.like(field, value));
                break;
            case isnull:
                queryWrapper.or(obj -> obj.isNotNull(field));
                break;
            case not:
                queryWrapper.or(obj -> obj.ne(field, value));
                break;
            case lt:
                queryWrapper.or(obj -> obj.lt(field, value));
                break;
            case gt:
                queryWrapper.or(obj -> obj.gt(field, value));
                break;
            case gte:
                queryWrapper.or(obj -> obj.ge(field, value));
                break;
            case lte:
                queryWrapper.or(obj -> obj.le(field, value));
                break;
            case nin:
                queryWrapper.or(obj -> obj.notIn(field, value));
                break;
            case nlike:
                queryWrapper.or(obj -> obj.notLike(field, value));
                break;
            case notnull:
                queryWrapper.or(obj -> obj.isNotNull(field));
                break;
            default:
                throw new NotSupportedException(String.format("operator[%s] not supported in mybatis or TermType.", field));

        }
    }
}
