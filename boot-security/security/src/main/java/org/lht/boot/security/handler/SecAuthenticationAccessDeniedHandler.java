package org.lht.boot.security.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.lht.boot.security.common.config.SecProperties;
import org.lht.boot.security.common.constant.SecurityConstant;
import org.lht.boot.security.common.util.RequestUtil;
import org.lht.boot.web.api.param.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiHaitao
 * @description 无权限处理器
 * @date 2020/3/25 19:50
 **/
public class SecAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    private ObjectMapper mapper = new ObjectMapper();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecProperties secProperties;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //返回无权限结果
        if (RequestUtil.isAjaxRequest(request)) {
            response.setContentType(SecurityConstant.JSON_UTF8);
            response.getWriter().write(this.mapper.writeValueAsString(R.error("没有该权限！")));
        } else {
            //无权限重定向页面
            redirectStrategy.sendRedirect(request, response, secProperties.getAccessDenyUrl());
        }
    }
}
