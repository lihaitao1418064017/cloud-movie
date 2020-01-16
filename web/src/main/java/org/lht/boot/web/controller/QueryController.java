package org.lht.boot.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.api.param.R;
import org.lht.boot.web.api.param.util.ParamServletUtil;
import org.lht.boot.web.service.QueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description QueryController:
 * @date 2020/1/14 18:38
 **/
public interface QueryController<E, PK, VO, Q extends Param> {

    <S extends QueryService<E, PK>> S getService();

    /**
     * 根据id查询
     *
     * @param pk
     * @return
     */
    @GetMapping("/{pk}")
    @ResponseBody
    default R<VO> selectById(@PathVariable PK pk) {
        E result = getService().get(pk);
        Validate.notNull(result, "data not exist");
        return R.ok(entityToVo(result));
    }

    /**
     * 条件查询
     *
     * @param param
     * @param request
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    default R<List> select(Q param, HttpServletRequest request) {
        ParamServletUtil.paddingTerms(param, request);
        List<E> list = getService().select(param);
        if (CollectionUtil.isEmpty(list)) {
            return R.ok(Lists.newArrayList());
        }
        return R.ok(list.stream().map(this::entityToVo).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     *
     * @param param
     * @param request
     * @return
     */
    @GetMapping("/page")
    @ResponseBody
    default R<PagerResult<VO>> selectPage(Q param, HttpServletRequest request) {
        ParamServletUtil.paddingTerms(param, request);
        PagerResult<E> pagerResult = getService().selectPager(param);
        if (CollectionUtil.isEmpty(pagerResult.getRecords())) {
            return R.ok(new PagerResult<VO>());
        }
        return R.ok(pagerResult.convertTo(this::entityToVo));
    }

    VO entityToVo(E e);
}
