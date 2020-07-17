package org.lht.boot.security.server.dao;

import org.lht.boot.security.server.domain.entity.SystemBroadcast;
import org.lht.boot.web.dao.ElasticSearchCrudDao;
import org.springframework.stereotype.Repository;

/**
 * @description: 广告墙
 * @author: LiHaitao
 * @date: 2020/7/14 14:34
 */
@Repository
public class SystemBroadcastDao extends ElasticSearchCrudDao<SystemBroadcast, String> {


}
