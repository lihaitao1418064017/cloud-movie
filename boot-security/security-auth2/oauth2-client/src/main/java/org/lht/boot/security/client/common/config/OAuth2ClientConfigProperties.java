package org.lht.boot.security.client.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LiHaitao
 * @description OAuth2ClientConfigProperties:
 * @date 2020/4/28 20:08
 **/
@Data
@ConfigurationProperties(prefix = "boot.security.oauth2.client")
public class OAuth2ClientConfigProperties {

    /**
     * 请求code地址，authorize请求
     */
    private String serverUri;
    /***
     * 客户端地址，返回携带 code
     */
    private String clientUri;
    /**
     * 返回类型 oauth2.0 请求code的模式
     */
    private String responseType = "code";
    /**
     * 权限控制范围
     */
    private String scope = "scope";
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 授权类型 oauth2.0 请求accessToken的模式
     */
    private String grantType;//authorization_code


}
