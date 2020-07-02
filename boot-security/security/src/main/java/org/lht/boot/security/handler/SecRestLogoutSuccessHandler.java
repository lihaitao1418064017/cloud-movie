package org.lht.boot.security.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.web.api.param.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 登出成功过滤
 * @author: LiHaitao
 * @date: 2020/3/26 10:31
 */
@Slf4j
public class SecRestLogoutSuccessHandler implements LogoutSuccessHandler {


    @Autowired
    protected ApplicationContext context;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登出成功。。。。。。。。。。。。。。。");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding("UTF-8");

        // TODO: 2020/3/26 something

        R<Object> ok = R.ok();
        response.getWriter().println(JSONObject.toJSON(ok));
    }
}
