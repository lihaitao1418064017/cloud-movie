package org.lht.boot.web.service.impl;

import org.lht.boot.web.dao.DataDictDao;
import org.lht.boot.web.domain.entity.DataDict;
import org.lht.boot.web.service.DataDictService;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description 数据字典
 * @date 2020/7/10 14:37
 **/
@Service
public class DataDictServiceImpl extends CrudTreeServiceImpl<DataDict, String, DataDictDao> implements DataDictService {

}
