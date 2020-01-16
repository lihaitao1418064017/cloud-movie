package org.lht.boot.web.api.param.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.lht.boot.lang.util.ServletUtil;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.api.param.TermEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author LiHaitao
 * @description ParamServletUtil:param和HttpServletRequest之间的工具类
 * @date 2020/1/14 18:53
 **/
public class ParamServletUtil {

    public static final String OR_SEPARATOR = "_or_";
    public static final String AND_SEPARATOR = "_and_";

    public static final String DEFAULT_PREFIX = "f_";


    public static void paddingTerms(Param param, HttpServletRequest request) {
        List<Term> terms = buildFrom(request);
        param.addTerms(terms);
    }

    public static List<Term> buildFrom(HttpServletRequest request) {
        //从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
        Map<String, Object> filterParamMap = ServletUtil.getParametersStartingWith(request, DEFAULT_PREFIX);
        return buildFrom(filterParamMap);
    }

    /**
     * like_name
     *
     * @param filterParamMap
     * @return List<Term>
     */
    public static List<Term> buildFrom(Map<String, Object> filterParamMap) {
        List<Term> terms = Lists.newArrayList();
        /**分析参数Map,构造PropertyFilter列表*/
        for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
            /**获取_之前的字符串*/
            String typeName = StringUtils.substringBefore(entry.getKey(), "_");//like
            String name = StringUtils.substringAfter(entry.getKey(), "_");//name
            Object value = entry.getValue();
            //如果value值为空,则忽略此filter.
            if (value == null) {
                continue;
            }
            TermEnum matchType;
            try {
                matchType = Enum.valueOf(TermEnum.class, typeName);
                terms.add(Term.build(name, matchType, value));
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("filter名称" + typeName + "没有按规则编写,无法得到属性比较类型.", e);
            }

        }
        return terms;
    }

}
