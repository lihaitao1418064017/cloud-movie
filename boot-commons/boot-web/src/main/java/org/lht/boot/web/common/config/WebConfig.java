package org.lht.boot.web.common.config;

import org.lht.boot.cache.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 通用配置
 *
 * @Author lht
 * @Date 2020/4/1 下午8:21
 **/
@Configuration
public class WebConfig {

    @Bean
    public RedisUtil redisUtil(){
        return new RedisUtil();
    }
}
