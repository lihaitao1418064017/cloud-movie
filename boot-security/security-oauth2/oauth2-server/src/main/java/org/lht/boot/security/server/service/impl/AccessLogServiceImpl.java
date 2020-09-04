package org.lht.boot.security.server.service.impl;

import org.lht.boot.security.server.dao.AccessLogDao;
import org.lht.boot.security.server.domain.entity.AccessLog;
import org.lht.boot.security.server.service.AccessLogService;
import org.lht.boot.web.service.impl.AbstractEsCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description 访问日志
 * @date 2020/7/13 14:18
 **/
@Service
public class AccessLogServiceImpl extends AbstractEsCrudServiceImpl<AccessLog, String, AccessLogDao> implements AccessLogService {
}
