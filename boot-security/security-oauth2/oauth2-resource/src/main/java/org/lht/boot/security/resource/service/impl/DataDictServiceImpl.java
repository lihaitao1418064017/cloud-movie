package org.lht.boot.security.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lht.boot.security.resource.dao.DataDictDao;
import org.lht.boot.security.resource.entity.DataDict;
import org.lht.boot.security.resource.service.DataDictService;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.util.ParamMybatisUtil;
import org.lht.boot.web.service.impl.CrudTreeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description 数据字典
 * @date 2020/7/10 14:37
 **/
@Service
public class DataDictServiceImpl extends CrudTreeServiceImpl<DataDict, String, DataDictDao> implements DataDictService {


    @Override
    public PagerResult<DataDict> typePage(QueryParam queryParam) {
        QueryWrapper<DataDict> queryWrapper = ParamMybatisUtil.toQueryWrapper(queryParam);
        queryWrapper.and(dataDictQueryWrapper -> dataDictQueryWrapper.isNull("pid").or().eq("pid",""));
        PagerResult<DataDict> page = ParamMybatisUtil.buildPage(queryParam);
        IPage<DataDict> iPage = dao.selectPage(page, queryWrapper);
        page.setTotal(iPage.getTotal());
        page.setRecords(iPage.getRecords());
        page.setTotalPages(iPage.getPages());
        return page;
    }
}
