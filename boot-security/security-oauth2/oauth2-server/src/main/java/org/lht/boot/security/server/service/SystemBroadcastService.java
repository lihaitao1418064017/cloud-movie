package org.lht.boot.security.server.service;

import org.lht.boot.security.server.dao.SystemBroadcastDao;
import org.lht.boot.security.server.domain.entity.SystemBroadcast;
import org.lht.boot.web.service.AbstractEsCrudService;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/7/14 14:34
 */
public interface SystemBroadcastService extends AbstractEsCrudService<SystemBroadcast, String, SystemBroadcastDao> {
}
