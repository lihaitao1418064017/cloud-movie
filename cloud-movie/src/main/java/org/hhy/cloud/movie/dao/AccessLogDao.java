package org.hhy.cloud.movie.dao;

import org.hhy.cloud.movie.entity.AccessLog;
import org.lht.boot.web.dao.ElasticSearchCrudDao;
import org.springframework.stereotype.Repository;

/**
 * @description: log
 * @author: LiHaitao
 * @date: 2020/7/13 14:16
 */
@Repository
public class AccessLogDao extends ElasticSearchCrudDao<AccessLog, String> {


}
