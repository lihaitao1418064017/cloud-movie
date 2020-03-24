package org.lht.boot.security.common.session;

import lombok.Data;
import org.lht.boot.security.common.config.SecProperties;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 处理 session失效
 * @author lht
 * @date 2020-3-24
 */
@Data
public class SecInvalidSessionStrategy implements InvalidSessionStrategy {

    private SecProperties secProperties;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * session无效后重定向到登出页面
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, secProperties.getLogoutUrl());
    }


}
