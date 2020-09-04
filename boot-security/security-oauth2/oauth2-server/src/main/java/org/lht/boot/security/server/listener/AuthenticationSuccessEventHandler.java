package org.lht.boot.security.server.listener;

import lombok.extern.slf4j.Slf4j;
import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.security.server.service.SystemLoginInfoService;
import org.lht.boot.web.common.exception.JestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static org.lht.boot.security.server.common.constant.OAuth2CommonConstant.LOGIN_SUCCESS;

/**
 * @description: 认证成功事件
 * @author: LiHaitao
 * @date: 2020/7/15 16:44
 */
@Slf4j
@Component
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {


    @Autowired
    private SystemLoginInfoService systemLoginInfoService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = (Authentication) event.getSource();
        String username = authentication.getName();
        SystemLoginInfo info = new SystemLoginInfo();
        info.setUsername(username);
        info.setMsg(LOGIN_SUCCESS);
        try {
            systemLoginInfoService.add(info);
        } catch (JestException e) {
            log.error("jest error：{}", e);
        }
    }
}
