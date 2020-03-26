package org.lht.boot.security.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lht.boot.security.common.constant.SecurityConstant;
import org.lht.boot.web.api.param.R;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * 处理 session过期
 * 导致 session 过期的原因有：
 * 1. 并发登录控制
 * 2. 被踢出
 * @author lht
 * @date 2020-3-24
 */
public class SecExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * sessiont过期处理策略
     * @param event
     * @throws IOException
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        event.getResponse()
                .setContentType(SecurityConstant.JSON_UTF8);
        event.getResponse()
                .getWriter()
                .write(mapper
                        .writeValueAsString(R
                                .error("登录已失效")));
    }

}
