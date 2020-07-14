package org.lht.boot.security.server.service.impl;

import org.lht.boot.security.server.dao.SystemBroadcastDao;
import org.lht.boot.security.server.domain.entity.SystemBroadcast;
import org.lht.boot.security.server.service.SystemBroadcastService;
import org.lht.boot.web.service.impl.AbstractEsCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/7/14 14:35
 */
@Service
public class SystemBroadcastServiceImpl extends AbstractEsCrudServiceImpl<SystemBroadcast, String, SystemBroadcastDao> implements SystemBroadcastService {
}
