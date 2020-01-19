package org.lht.boot.cache.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description RedisCacheManageProperties:redisLock相关配置
 * @date 2020/1/19 15:35
 **/
@Data
@Configuration
@ConfigurationProperties(value = "cache.lock")
public class RedisLockProperties {


    private String singleServerAddress = "redis://localhost:6379";

    private Long entryTtl = 30L;

    private String keyPrefix;

    private boolean cacheNull;
}
