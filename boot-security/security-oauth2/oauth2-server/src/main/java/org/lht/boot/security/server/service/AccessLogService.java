package org.lht.boot.security.server.service;

import org.lht.boot.security.server.dao.AccessLogDao;
import org.lht.boot.security.server.domain.entity.AccessLog;
import org.lht.boot.web.service.AbstractEsCrudService;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/7/13 14:17
 */
public interface AccessLogService extends AbstractEsCrudService<AccessLog, String, AccessLogDao> {
}
