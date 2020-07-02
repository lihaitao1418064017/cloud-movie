package org.lht.boot.security.session;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.security.common.config.SecProperties;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 处理 session失效
 *
 * @author lht
 * @date 2020-3-24
 */
@Data
@Slf4j
public class SecInvalidSessionStrategy implements InvalidSessionStrategy {

    private SecProperties secProperties;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * session无效后重定向到登出页面
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        log.info("session:{}", session.getId());
        boolean aNew = session.isNew();
        if (!aNew) {
            redirectStrategy.sendRedirect(request, response, secProperties.getLogoutUrl());
        }
    }


}
