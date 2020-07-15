package org.lht.boot.security.server.service;


import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.web.service.BaseCrudService;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/14 17:08
 **/
public interface SystemLoginInfoService extends BaseCrudService<SystemLoginInfo, Integer> {


    Integer add(SystemLoginInfo systemLoginInfo);
}
