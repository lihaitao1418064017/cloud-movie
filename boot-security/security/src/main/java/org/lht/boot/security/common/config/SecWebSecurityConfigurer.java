package org.lht.boot.security.common.config;

import org.lht.boot.security.handler.SecAuthenticationFailureHandler;
import org.lht.boot.security.handler.SecAuthenticationLogoutHandler;
import org.lht.boot.security.handler.SecAuthenticationSuccessHandler;
import org.lht.boot.security.handler.SecRestLogoutSuccessHandler;
import org.lht.boot.security.session.SecExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.web.cors.CorsUtils;

import javax.sql.DataSource;


/**
 * 配置Spring Security初始化
 *
 * @author lht
 * @date 2020-2-30 19:34
 **/
@EnableConfigurationProperties(SecProperties.class)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Order(99)
public class SecWebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecAuthenticationSuccessHandler secAuthenticationSuccessHandler;

    @Autowired
    private SecAuthenticationFailureHandler secAuthenticationFailureHandler;

    @Autowired
    private SecProperties secProperties;


    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private SecAuthenticationLogoutHandler secAuthenticationLogoutHandler;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private SecRestLogoutSuccessHandler secRestLogoutSuccessHandler;


    /**
     * security密码加密工具类
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 处理 rememberMe 自动登录认证
     *
     * @return PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);// 启动创建表，创建成功后注释掉
        return jdbcTokenRepository;
    }

    /**
     * 认证管理器
     *
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManager();
    }


    /**
     * url 配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.cors().and().csrf().disable().authorizeRequests()
                //处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll().and().exceptionHandling()
                // 权限不足处理器
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                //                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 短信验证码校验
                //过滤前验证
                //.addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加图形证码校验过滤器
                // 表单方式
                .formLogin()
                .loginPage(secProperties.getLoginUrl())
                .successHandler(secAuthenticationSuccessHandler)
                // 处理登录失败
                .failureHandler(secAuthenticationFailureHandler)
                // 未认证跳转 URL
                // 处理登录认证 URL
                .loginProcessingUrl(secProperties.getLoginUrl())
                .and().exceptionHandling().accessDeniedPage(secProperties.getAccessDenyUrl())
                .and().logout().logoutUrl(secProperties.getLogoutUrl())
                // 处理登出成功
                .logoutSuccessHandler(secRestLogoutSuccessHandler)
                .and()
                // 添加记住我功能,登录添加remember-me=true条件
                .rememberMe()
                .userDetailsService(userDetailsService)
                // 配置 token 持久化仓库
                .tokenRepository(persistentTokenRepository())
                // rememberMe 过期时间，单为秒
                .tokenValiditySeconds(secProperties.getRememberMeTimeout())
                //                // 处理自动登录逻辑
                //                .userDetailsService(secUserDetailService)
                .and()
                // 配置 session管理器
                .sessionManagement()
                // 处理 session失效
                //                .invalidSessionUrl("/login/invalid")
                .invalidSessionStrategy(invalidSessionStrategy)
                // 最大并发登录数量
                .maximumSessions(secProperties.getSession().getMaximumSessions())
                // 处理并发登录被踢出
                .expiredSessionStrategy(new SecExpiredSessionStrategy())
                // 配置 session注册中心
                .sessionRegistry(sessionRegistry)
                .expiredUrl(secProperties.getLoginUrl())
                .and()
                .and()
                // 配置登出
                .logout()
                //                // 配置登出处理器
                //                .addLogoutHandler(secAuthenticationLogoutHandler)
                //                // 处理登出 url
                //                .logoutUrl(secProperties.getLogoutUrl())
                // 登出后跳转到 /
                .logoutSuccessUrl("/")
                // 删除 JSESSIONID
                .deleteCookies("JSESSIONID")
                .and()
                // 授权配置
                .authorizeRequests()
                .antMatchers(
                        // 免认证路径

                        secProperties.getPermitAll().toArray(new String[secProperties.getPermitAll().size()])
                ).permitAll()
                // 所有请求
                .anyRequest()
                // 都需要认证
                .authenticated();

    }


}
