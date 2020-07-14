package org.lht.boot.security.resource.service.impl;


import org.lht.boot.security.resource.dao.SystemLoginInfoDao;
import org.lht.boot.security.resource.entity.SystemLoginInfo;
import org.lht.boot.security.resource.service.SystemLoginInfoService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/7/14 20:26
 */
@Service
public class SystemLoginInfoServiceImpl extends BaseMybatisCrudServiceImpl<SystemLoginInfo, Integer, SystemLoginInfoDao> implements SystemLoginInfoService {


}
