package org.lht.boot.security.resource.dao;

import org.lht.boot.security.resource.entity.DataDict;
import org.lht.boot.web.dao.CrudTreeDao;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description 数据字典
 * @date 2020/7/10 14:35
 **/
@Repository
public interface DataDictDao extends CrudTreeDao<DataDict> {
}
