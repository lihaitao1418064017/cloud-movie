package org.hhy.cloud.movie.service.impl;


import org.hhy.cloud.movie.dao.AccessLogDao;
import org.hhy.cloud.movie.entity.AccessLog;
import org.hhy.cloud.movie.service.AccessLogService;
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
