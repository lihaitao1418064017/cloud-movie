package org.lht.boot.security.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LiHaitao
 * @description Security通用配置
 * @date 2020/3/24 21:58
 **/
@ConfigurationProperties(prefix = "security.url")
@Data
public class SecProperties {

    /**
     * 登录 URL
     */
    private String loginUrl;
    /**
     * 免认证静态资源路径
     */
    private String anonResourcesUrl;
    /**
     * 记住我超时时间
     */
    private int rememberMeTimeout;
    /**
     * 登出
     */
    private String logoutUrl;
    /**
     * 主页 URL
     */
    private String indexUrl;

    /**
     * 认证url
     */
    private String authUrl;

    /**
     * session相关配置
     */
    private SessionProperties session = new SessionProperties();


}
