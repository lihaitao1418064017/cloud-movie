package org.lht.boot.security.common.session;

import org.lht.boot.security.common.config.SecProperties;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理 session 失效
 */
public class SecInvalidSessionStrategy implements InvalidSessionStrategy {

    private SecProperties secProperties;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, secProperties.getLogoutUrl());
    }

    public SecProperties getSecProperties() {
        return secProperties;
    }

    public void setSecProperties(SecProperties securityProperties) {
        this.secProperties = securityProperties;
    }
}
