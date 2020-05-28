package org.lht.boot.security.server.config;

import org.lht.boot.security.config.config.OAuth2ServerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * @description: OAuth2 授权服务器配置
 * @author: LiHaitao
 * @date: 2020/4/26 19:38
 */
@Configuration
public class OAuth2AuthorizationServerConfig extends OAuth2ServerConfig {


    /**
     * 调用父类构造函数，设置令牌失效日期等信息
     */
    public OAuth2AuthorizationServerConfig() {
        super((int) TimeUnit.DAYS.toSeconds(1), 0, false, false);
    }

    /**
     * 配置客户端详情
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        //        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
        clients.inMemory()                          // 使用内存存储客户端信息
                .withClient("boot-oauth2")       // client_id
                .redirectUris("http://localhost:8081/oauth2/callback")
                .secret("oauth2")                   // client_secret
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "client_credentials")     // 该client允许的授权类型
                .accessTokenValiditySeconds(3600)               // Token 的有效期
                .scopes("scope")                    // 允许的授权范围
                .autoApprove(true);                  //登录后绕过批准询问(/oauth/confirm_access)
    }
}
