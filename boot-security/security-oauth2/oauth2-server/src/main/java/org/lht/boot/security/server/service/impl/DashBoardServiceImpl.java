package org.lht.boot.security.server.service.impl;

import org.lht.boot.lang.util.ServletUtil;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.server.common.util.LoginAddressUtil;
import org.lht.boot.security.server.domain.entity.DashBoard;
import org.lht.boot.security.server.service.DashBoardService;
import org.lht.boot.security.server.service.SystemBroadcastService;
import org.lht.boot.security.server.service.SystemLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description
 * @date 2020/9/3 16:08
 **/
@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    private SystemLoginInfoService systemLoginInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SystemBroadcastService systemBroadcastService;

    @Override
    public DashBoard select(String username) {
        DashBoard dashBoard = new DashBoard();
        //获取登录市级地址
        dashBoard.setLastLoginAddress(LoginAddressUtil.getCity(ServletUtil.getIpAddr(ServletUtil.getServletRequest())));
        dashBoard
        return null;
    }
}
