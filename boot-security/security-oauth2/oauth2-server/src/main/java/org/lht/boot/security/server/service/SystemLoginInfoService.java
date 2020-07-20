package org.lht.boot.security.server.service;


import org.lht.boot.security.server.dao.SystemLoginInfoDao;
import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.web.service.AbstractEsCrudService;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/14 17:08
 **/
public interface SystemLoginInfoService extends AbstractEsCrudService<SystemLoginInfo, String, SystemLoginInfoDao> {


    String add(SystemLoginInfo systemLoginInfo);
}
