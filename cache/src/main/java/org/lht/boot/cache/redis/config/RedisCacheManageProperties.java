package org.lht.boot.cache.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description RedisCacheManageProperties:cachemanageer相关配置
 * @date 2020/1/19 15:35
 **/
@Data
@Configuration
@ConfigurationProperties(value = "cache.redis.manage")
public class RedisCacheManageProperties {


    private String cacheConfig = "redisExpire";

    private Long entryTtl = 30L;

    private String keyPrefix;

    private boolean cacheNull;
}
