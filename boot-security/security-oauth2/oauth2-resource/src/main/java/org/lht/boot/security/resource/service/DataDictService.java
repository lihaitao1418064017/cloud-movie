package org.lht.boot.security.resource.service;

import org.lht.boot.security.resource.entity.DataDict;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.service.CrudTreeService;

/**
 * @author LiHaitao
 * @description 数据字典
 * @date 2020/7/10 14:36
 **/
public interface DataDictService extends CrudTreeService<DataDict, String> {

    /**
     * 获取字典类型
     * @param queryParam
     * @return
     */
    PagerResult<DataDict> typePage(QueryParam queryParam);
}
