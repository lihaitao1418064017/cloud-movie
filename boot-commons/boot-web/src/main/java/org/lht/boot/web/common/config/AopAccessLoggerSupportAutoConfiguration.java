package org.lht.boot.web.common.config;

import org.lht.boot.web.common.aspect.AopAccessLoggerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 自动配置
 * @author: LiHaitao
 * @date: 2020/7/13 14:13
 */
@Configuration
public class AopAccessLoggerSupportAutoConfiguration {
    public AopAccessLoggerSupportAutoConfiguration() {
    }

    @Bean
    public AopAccessLoggerSupport aopAccessLoggerSupport() {
        return new AopAccessLoggerSupport();
    }
}
