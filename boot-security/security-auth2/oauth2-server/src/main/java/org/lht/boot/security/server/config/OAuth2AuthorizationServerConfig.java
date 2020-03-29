package org.lht.boot.security.server.config;

import org.lht.boot.security.config.config.AuthServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * @description: OAuth2 授权服务器配置
 */
@Configuration
public class OAuth2AuthorizationServerConfig extends AuthServerConfig {

    @Resource
    private DataSource dataSource;

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
                .withClient("resource1")       // client_id
                .secret("secret")                   // client_secret
                .authorizedGrantTypes("authorization_code", "password","client_credentials","client_credentials")     // 该client允许的授权类型
                .accessTokenValiditySeconds(3600)               // Token 的有效期
                .scopes("read")                    // 允许的授权范围
                .autoApprove(true);                  //登录后绕过批准询问(/oauth/confirm_access)
    }
}
