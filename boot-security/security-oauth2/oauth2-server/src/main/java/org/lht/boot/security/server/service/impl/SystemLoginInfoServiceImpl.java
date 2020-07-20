package org.lht.boot.security.server.service.impl;


import org.lht.boot.lang.util.IpUtils;
import org.lht.boot.lang.util.ServletUtil;
import org.lht.boot.security.server.dao.SystemLoginInfoDao;
import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.security.server.service.SystemLoginInfoService;
import org.lht.boot.web.service.impl.AbstractEsCrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/7/14 20:26
 */
@Service
public class SystemLoginInfoServiceImpl extends AbstractEsCrudServiceImpl<SystemLoginInfo, String, SystemLoginInfoDao> implements SystemLoginInfoService {


    @Override
    public String add(SystemLoginInfo data) {
        data.setAccessTime(new Date());
        data.setIpAddress(IpUtils.getIpAddr(ServletUtil.getServletRequest()));
        return insert(data);
    }
}
