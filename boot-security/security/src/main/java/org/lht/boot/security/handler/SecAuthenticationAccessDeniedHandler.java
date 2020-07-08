package org.lht.boot.security.handler;


import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lht.boot.security.common.config.SecProperties;
import org.lht.boot.security.common.constant.SecurityConstant;
import org.lht.boot.web.api.param.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiHaitao
 * @description 无权限处理器
 * @date 2020/3/25 19:50
 **/
@Component
public class SecAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    private ObjectMapper mapper = new ObjectMapper();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecProperties secProperties;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //        //返回无权限结果
        //        if (RequestUtil.isAjaxRequest(request)) {
        //            response.setContentType(SecurityConstant.JSON_UTF8);
        //            R<Object> error = R.error("没有该权限！");
        //            error.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        //            response.getWriter().write(this.mapper.writeValueAsString(error));
        //        } else {
        //            //无权限重定向页面
        //            redirectStrategy.sendRedirect(request, response, secProperties.getAccessDenyUrl());
        //        }

        response.setContentType(SecurityConstant.JSON_UTF8);
        R<Object> error = R.error("没有该权限！");
        error.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        response.getWriter().write(this.mapper.writeValueAsString(error));
    }
}
