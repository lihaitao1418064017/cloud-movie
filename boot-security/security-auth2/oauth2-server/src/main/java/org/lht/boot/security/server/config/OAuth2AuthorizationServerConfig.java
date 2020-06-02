package org.lht.boot.security.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @description: OAuth2 授权服务器配置
 * @author: LiHaitao
 * @date: 2020/4/26 19:38
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    DataSource dataSource;


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private ClientDetailsService clientDetailsService;


    //令牌失效时间
    public int accessTokenValiditySeconds;

    //刷新令牌失效时间
    public int refreshTokenValiditySeconds;

    //是否可以重用刷新令牌
    public boolean isReuseRefreshToken;

    //是否支持刷新令牌
    public boolean isSupportRefreshToken;


    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }


    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置授权服务器端点，如令牌存储，令牌自定义，用户批准和授权类型，不包括端点安全配置
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));


        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setReuseRefreshToken(isReuseRefreshToken);
        defaultTokenServices.setSupportRefreshToken(isSupportRefreshToken);
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        defaultTokenServices.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        //若通过 JDBC 存储令牌
        if (Objects.nonNull(clientDetailsService)) {
            defaultTokenServices.setClientDetailsService(clientDetailsService);
        }

        endpoints.authenticationManager(authenticationManager);


    }


    /**
     * 配置授权服务器端点的安全
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
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
        clients.withClientDetails(clientDetailsService());


        //        //        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
        //        clients.inMemory()                          // 使用内存存储客户端信息
        //                .withClient("boot-oauth2")       // client_id
        //                .redirectUris("http://localhost:8081/oauth2/callback")
        //                .secret("oauth2")                   // client_secret
        //                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "client_credentials")     // 该client允许的授权类型
        //                .accessTokenValiditySeconds(3600)               // Token 的有效期
        //                .scopes("scope")                    // 允许的授权范围
        //                .autoApprove(true);                  //登录后绕过批准询问(/oauth/confirm_access)
    }
}
