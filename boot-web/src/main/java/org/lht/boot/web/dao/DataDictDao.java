package org.lht.boot.web.dao;

import org.lht.boot.web.domain.entity.DataDict;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description 数据字典
 * @date 2020/7/10 14:35
 **/
@Repository
public interface DataDictDao extends CrudTreeDao<DataDict> {
}
