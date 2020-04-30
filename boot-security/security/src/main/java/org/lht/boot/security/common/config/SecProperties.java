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
     * 用户注册 URL
     */
    public String registerUrl = "/user/register";
    /**
     * 权限不足 URL
     */
    public String accessDenyUrl = "/access/deny/403";


    /**
     * 登录 URL
     */
    private String loginUrl = "/auth/login";

    private String swaggerUrl = "/swagger-ui.html/**";

    /**
     * 免认证静态资源路径
     */
    private String anonResourcesUrl = "/css";
    /**
     * 记住我超时时间
     */
    private int rememberMeTimeout = 30000;
    /**
     * 登出
     */
    private String logoutUrl = "/logout";
    /**
     * 主页 URL
     */
    private String indexUrl = "/index";

    /**
     * 认证url
     */
    private String authUrl = "/auth/login";

    /**
     * session相关配置
     */
    private SessionProperties session = new SessionProperties();

    private String defaultRolePrefix;


}
