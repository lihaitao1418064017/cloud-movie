package org.lht.boot.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.lang.util.R;
import org.lht.boot.security.common.constant.SecurityConstant;
import org.lht.boot.security.common.exception.SecException;
import org.lht.boot.security.common.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiHaitao
 * @description 登陆失败处理器
 * @date 2020/3/25 19:50
 **/
@Slf4j
@Component
public class SecAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String message;
        log.info("登录发生异常：{}", exception.getMessage());
        if (exception instanceof UsernameNotFoundException) {
            message = "用户不存在！";
        } else if (exception instanceof BadCredentialsException) {
            message = "用户名或密码错误！";
        } else if (exception instanceof LockedException) {
            message = "用户已被锁定！";
        } else if (exception instanceof DisabledException) {
            message = "用户不可用！";
        } else if (exception instanceof AccountExpiredException) {
            message = "账户已过期！";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "用户密码已过期！";
        } else if (exception instanceof ValidateCodeException || exception instanceof SecException) {
            message = exception.getMessage();
        } else {
            message = "认证失败，请联系网站管理员！";
        }
        response.setContentType(SecurityConstant.JSON_UTF8);
        response.getWriter().write(mapper.writeValueAsString(R.error(message)));
    }
}

