package org.lht.boot.mq;

import org.lht.boot.mq.redis.lock.RedisLockProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description AutoConfig:
 * @date 2020/1/19 15:49
 **/
@Configuration
@EnableConfigurationProperties({RedisLockProperties.class})
public class AutoConfig {
}
