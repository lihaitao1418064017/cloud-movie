package org.lht.boot.web.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description MybatisConfig:MybatisPlus 配置
 * @date 2020/1/9 16:29
 **/
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisConfig {


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
