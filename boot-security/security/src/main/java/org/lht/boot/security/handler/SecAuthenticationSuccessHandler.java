package org.lht.boot.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lht.boot.security.common.config.SecProperties;
import org.lht.boot.security.common.constant.SecurityConstant;
import org.lht.boot.security.entity.LoginType;
import org.lht.boot.security.entity.SecUserDetails;
import org.lht.boot.web.api.param.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import org.lht.boot.security.entity.SecUserDetails;

/**
 * @author LiHaitao
 * @description 登陆成功处理器
 * @date 2020/3/25 19:50
 **/
@Configuration
public class SecAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper mapper = new ObjectMapper();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private SessionRegistry sessionRegistry;

    @Autowired
    private SecProperties secProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

        LoginType loginType = LoginType.NORMAL;
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecUserDetails) {
            SecUserDetails userDetails = (SecUserDetails) principal;
            userDetails.setRemoteAddress(remoteAddress);
            loginType = userDetails.getLoginType();
        }

        // 解决第三方登录在 session 并发控制设置不生效的问题
        if (!LoginType.NORMAL.equals(loginType)) {
            String sessionId = details.getSessionId();
            sessionRegistry.removeSessionInformation(sessionId);
            sessionRegistry.registerNewSession(sessionId, authentication.getPrincipal());

            ConcurrentSessionControlAuthenticationStrategy authenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);

            // 手动设置最大并发登录数量，因为在 sessionManagement 中设置的 maximumSessions
            // 只对账号密码登录的方式生效 =。=
            authenticationStrategy.setMaximumSessions(secProperties.getSession().getMaximumSessions());
            authenticationStrategy.onAuthentication(authentication, request, response);

            //            // 社交账户登录成功后直接 重定向到主页
            //            if (LoginType.SOCIAL.equals(loginType))
            //                redirectStrategy.sendRedirect(request, response, secProperties.getIndexUrl());
        }
        response.setContentType(SecurityConstant.JSON_UTF8);
        response.getWriter().write(mapper.writeValueAsString(R.ok()));
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }
}
