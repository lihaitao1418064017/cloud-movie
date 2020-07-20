package org.lht.boot.security.server.dao;

import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.web.dao.ElasticSearchCrudDao;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/7/14 20:11
 */
@Repository
public class SystemLoginInfoDao extends ElasticSearchCrudDao<SystemLoginInfo, String> {

}
