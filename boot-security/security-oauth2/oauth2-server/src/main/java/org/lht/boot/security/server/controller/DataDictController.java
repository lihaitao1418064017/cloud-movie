package org.lht.boot.security.server.controller;

import org.lht.boot.lang.util.R;
import org.lht.boot.security.resource.entity.DataDict;
import org.lht.boot.security.resource.service.DataDictService;
import org.lht.boot.security.resource.vo.DataDictVO;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.util.ParamServletUtil;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.controller.AbstractTreeController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 数据字典
 *
 * @Author lht
 * @Date 2020/7/10 下午9:23
 **/
@RestController
@RequestMapping("/data_dict")
@AccessLogger("数据字典")
public class DataDictController extends AbstractTreeController<DataDict, String, DataDictVO, DataDictService> {


    /**
     * 获取所有字典类型
     *
     * @param param
     * @param request
     * @return
     */
    @GetMapping("/page/type")
    public R<PagerResult<DataDictVO>> typePage(QueryParam param, HttpServletRequest request) {
        ParamServletUtil.paddingTerms(param, request);
        return R.ok(service.typePage(param).convertTo(this::entityToVo));
    }
}
