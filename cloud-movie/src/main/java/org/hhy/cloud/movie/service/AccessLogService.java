package org.hhy.cloud.movie.service;

import org.hhy.cloud.movie.dao.AccessLogDao;
import org.hhy.cloud.movie.entity.AccessLog;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.service.AbstractEsCrudService;

/**
 * @description: 访问日志
 * @author: LiHaitao
 * @date: 2020/7/13 14:17
 */
public interface AccessLogService extends AbstractEsCrudService<AccessLog, String, AccessLogDao> {
}
