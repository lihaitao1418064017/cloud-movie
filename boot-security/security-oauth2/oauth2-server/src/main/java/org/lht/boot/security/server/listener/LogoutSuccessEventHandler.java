package org.lht.boot.security.server.listener;

import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.security.server.service.SystemLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static org.lht.boot.security.server.common.constant.OAuth2CommonConstant.LOGOUT_SUCCESS;

/**
 * @description: 登出成功事件
 * @author: LiHaitao
 * @date: 2020/7/15 16:44
 */
@Component
public class LogoutSuccessEventHandler implements ApplicationListener<LogoutSuccessEvent> {


    @Autowired
    private SystemLoginInfoService systemLoginInfoService;

    @Override
    public void onApplicationEvent(LogoutSuccessEvent event) {
        Authentication authentication = (Authentication) event.getSource();
        String username = authentication.getName();
        SystemLoginInfo info = new SystemLoginInfo();
        info.setUsername(username);
        info.setMsg(LOGOUT_SUCCESS);
        systemLoginInfoService.add(info);

    }
}
