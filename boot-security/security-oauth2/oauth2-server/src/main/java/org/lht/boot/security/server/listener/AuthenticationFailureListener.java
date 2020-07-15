package org.lht.boot.security.server.listener;

import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.security.server.service.SystemLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author LiHaitao
 * @description 登录失败事件
 * @date 2020/7/15 17:56
 **/
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Autowired
    private SystemLoginInfoService systemLoginInfoService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        Authentication authentication = authenticationFailureBadCredentialsEvent.getAuthentication();
        String username = authentication.getName();
        SystemLoginInfo info = new SystemLoginInfo();
        info.setUsername(username);
        info.setMsg("登录失败，用户名或密码错误！");
        systemLoginInfoService.add(info);
    }


}
