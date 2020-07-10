package org.lht.boot.security.resource.service.impl;

import org.lht.boot.security.resource.dao.DataDictDao;
import org.lht.boot.security.resource.service.DataDictService;
import org.lht.boot.security.resource.entity.DataDict;
import org.lht.boot.web.service.impl.CrudTreeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description 数据字典
 * @date 2020/7/10 14:37
 **/
@Service
public class DataDictServiceImpl extends CrudTreeServiceImpl<DataDict, String, DataDictDao> implements DataDictService {

}
