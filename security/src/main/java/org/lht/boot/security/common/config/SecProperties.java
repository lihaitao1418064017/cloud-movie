package org.lht.boot.security.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.url")
@Data
public class SecProperties {

    // 登录 URL
    private String loginUrl;
    // 免认证静态资源路径
    private String anonResourcesUrl;
    // 记住我超时时间
    private int rememberMeTimeout;
    // 登出 URL
    private String logoutUrl;
    // 主页 URL
    private String indexUrl;

    private ValidateCodeProperties code = new ValidateCodeProperties();


    private SessionProperties session = new SessionProperties();


}
