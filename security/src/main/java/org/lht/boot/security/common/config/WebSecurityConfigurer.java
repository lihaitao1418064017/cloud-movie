package org.lht.boot.security.common.config;

import org.apache.commons.lang3.StringUtils;
import org.lht.boot.security.common.constant.SecurityConstant;
import org.lht.boot.security.common.session.SecExpiredSessionStrategy;
import org.lht.boot.security.handler.SecAuthenticationFailureHandler;
import org.lht.boot.security.handler.SecAuthenticationLogoutHandler;
import org.lht.boot.security.handler.SecAuthenticationSuccessHandler;
import org.lht.boot.security.service.SecUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.sql.DataSource;

//import org.lht.boot.security.common.xss.XssFilter;

/**
 * 配置Spring Security初始化
 *
 * @create 2017-11-30 19:34
 **/
@EnableConfigurationProperties(SecProperties.class)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecAuthenticationSuccessHandler secAuthenticationSuccessHandler;

    @Autowired
    private SecAuthenticationFailureHandler secAuthenticationFailureHandler;

    @Autowired
    private SecProperties secProperties;


    @Autowired
    private SecUserDetailService secUserDetailService;

    @Autowired
    private DataSource dataSource;


    @Autowired
    private SecAuthenticationLogoutHandler secAuthenticationLogoutHandler;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    // spring security自带的密码加密工具类
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 处理 rememberMe 自动登录认证
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //静态资源
        String[] anonResourcesUrl = StringUtils
                .splitByWholeSeparatorPreserveAllTokens(secProperties.getAnonResourcesUrl(), ",");


        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler) // 权限不足处理器
                .and()
                //                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 短信验证码校验
                //                .addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加图形证码校验过滤器
                .formLogin() // 表单方式
                .loginPage(secProperties.getLoginUrl()) // 未认证跳转 URL
                .loginProcessingUrl(secProperties.getAuthUrl()) // 处理登录认证 URL
                .successHandler(secAuthenticationSuccessHandler) // 处理登录成功
                .failureHandler(secAuthenticationFailureHandler) // 处理登录失败
                .and()
                .rememberMe() // 添加记住我功能
                .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                .tokenValiditySeconds(secProperties.getRememberMeTimeout()) // rememberMe 过期时间，单为秒
                .userDetailsService(secUserDetailService) // 处理自动登录逻辑
                .and()
                .sessionManagement() // 配置 session管理器
                .invalidSessionStrategy(invalidSessionStrategy) // 处理 session失效
                .maximumSessions(secProperties.getSession().getMaximumSessions()) // 最大并发登录数量
                .expiredSessionStrategy(new SecExpiredSessionStrategy()) // 处理并发登录被踢出
                .sessionRegistry(sessionRegistry) // 配置 session注册中心
                .and()
                .and()
                .logout() // 配置登出
                .addLogoutHandler(secAuthenticationLogoutHandler) // 配置登出处理器
                .logoutUrl(secProperties.getLogoutUrl()) // 处理登出 url
                .logoutSuccessUrl("/") // 登出后跳转到 /
                .deleteCookies("JSESSIONID") // 删除 JSESSIONID
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers(anonResourcesUrl).permitAll() // 免认证静态资源路径
                .antMatchers(
                        secProperties.getLoginUrl(), // 登录路径
                        SecurityConstant.FEBS_REGIST_URL// 用户注册 url
                        //                        secProperties.getCode().getImage().getCreateUrl(), // 创建图片验证码路径
                        //                        secProperties.getCode().getSms().getCreateUrl(), // 创建短信验证码路径
                        //                        secProperties.getSocial().getSocialRedirectUrl(), // 重定向到社交账号注册（绑定）页面路径
                        //                        secProperties.getSocial().getSocialBindUrl(), // 社交账号绑定 URL
                        //                        secProperties.getSocial().getSocialRegistUrl() // 注册并绑定社交账号 URL
                ).permitAll() // 配置免认证路径
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and()
                .csrf().disable();

    }


}
