package org.lht.boot.mq.redis.config;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lht.boot.mq.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Redis配置类
 * @Author: Lihaitao
 * @Date: 2020/1/17 15:34
 * @UpdateUser:
 * @UpdateRemark:
 */
@Configuration
@EnableCaching
public class RedisConfig {


    @Autowired
    private RedisCacheManageProperties manageProperties;

    @Bean
    CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put(manageProperties.getCacheConfig(), redisCacheConfiguration());
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, redisCacheConfiguration(), redisCacheConfigurationMap);
        return cacheManager;
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisSerializationContext.SerializationPair keyPair = RedisSerializationContext
                .SerializationPair
                .fromSerializer(new StringRedisSerializer());
        RedisSerializationContext.SerializationPair valuePair = RedisSerializationContext
                .SerializationPair
                .fromSerializer(new StringRedisSerializer());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(manageProperties.getEntryTtl()))
                .serializeValuesWith(valuePair)
                .serializeKeysWith(keyPair);
        if (StrUtil.isBlank(manageProperties.getKeyPrefix())) {
            redisCacheConfiguration.disableKeyPrefix();
        } else {
            redisCacheConfiguration.prefixKeysWith(manageProperties.getKeyPrefix());
        }
        if (manageProperties.isCacheNull()) {
            redisCacheConfiguration.disableCachingNullValues();
        }
        return redisCacheConfiguration;
    }

    @Bean
    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        return serializer;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(jackson2JsonRedisSerializer());
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(RedisUtil.class)
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }

}