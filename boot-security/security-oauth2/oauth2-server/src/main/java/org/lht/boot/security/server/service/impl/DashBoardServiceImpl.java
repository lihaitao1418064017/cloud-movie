package org.lht.boot.security.server.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.server.domain.entity.DashBoard;
import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.security.server.service.DashBoardService;
import org.lht.boot.security.server.service.SystemBroadcastService;
import org.lht.boot.security.server.service.SystemLoginInfoService;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Sort;
import org.lht.boot.web.api.param.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.lht.boot.security.server.common.constant.OAuth2CommonConstant.LOGIN_SUCCESS;
import static org.lht.boot.security.server.common.constant.OAuth2CommonConstant.LOGOUT_SUCCESS;

/**
 * @author LiHaitao
 * @description 主页
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
        //获取登录市级地址,前端获取/*LoginAddressUtil.getCity(ServletUtil.getIpAddr(ServletUtil.getServletRequest()))*/
        dashBoard.setLastLoginAddress("");
        dashBoard.setLastLoginTime(this.getLastLoginTime(username));
        dashBoard.setLoginNumber(this.getLastLoginNumber(username));
        dashBoard.setTotalMessage(systemBroadcastService.count());
        dashBoard.setTotalUser(userInfoService.count());
        return null;
    }

    /**
     * 根据用户名获取上次登录时间
     *
     * @param username 用户名
     * @return 格式化日期 yyyy-MM-dd HH:mm:ss
     */
    private String getLastLoginTime(String username) {
        QueryParam queryParam = new QueryParam();
        queryParam.addTerm(Term.build("username", username));
        queryParam.addTerm(Term.build("msg", LOGOUT_SUCCESS));
        queryParam.sort(Lists.newArrayList(new Sort().desc("accessTime")));
        SystemLoginInfo systemLoginInfo = systemLoginInfoService.selectSingle(queryParam);
        Date accessTime = systemLoginInfo.getAccessTime();
        return DateUtil.format(accessTime, DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * 根据用户名用户登录次数
     *
     * @param username 用户名
     * @return 登录次数
     */
    private Long getLastLoginNumber(String username) {
        QueryParam queryParam = new QueryParam();
        queryParam.addTerm(Term.build("username", username));
        queryParam.addTerm(Term.build("msg", LOGIN_SUCCESS));
        return systemLoginInfoService.count(queryParam);
    }


}
