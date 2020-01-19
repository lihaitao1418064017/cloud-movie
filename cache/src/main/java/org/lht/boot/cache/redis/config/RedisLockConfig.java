package org.lht.boot.cache.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description RedisLockConfig:redis 锁配置
 * @date 2020/1/19 14:57
 **/
@Configuration
public class RedisLockConfig {


    @Autowired
    private RedisLockProperties redisLockProperties;

    @Bean
    public RLock rLock() {
        Config config = new Config();
        config.useSingleServer().setAddress(redisLockProperties.getSingleServerAddress());
        RedissonClient redisson = Redisson.create(config);
        return redisson.getLock("lock");
    }
}
