package org.lht.boot.security.server.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LiHaitao
 * @description OAuth2ServerConfigProperties:服务端配置
 * @date 2020/6/3 9:58
 **/
@Data
@ConfigurationProperties("boot.security.oauth2.client")
public class OAuth2ServerConfigProperties {


    /**
     * 令牌失效时间
     */
    private Integer accessTokenValiditySeconds = 36;

    /**
     * 刷新令牌失效时间
     */
    private Integer refreshTokenValiditySeconds = 360_000_000;

    /**
     * 是否可以重用刷新令牌
     */
    private Boolean isReuseRefreshToken = false;

    /**
     * 是否支持刷新令牌
     */
    private Boolean isSupportRefreshToken = false;


}
