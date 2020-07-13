package org.lht.boot.security.server.dao;

import org.lht.boot.security.server.domain.entity.AccessLog;
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
