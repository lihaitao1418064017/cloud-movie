package org.lht.boot.cache.guava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author LiHaitao
 * @description GuavaConfig:
 * @date 2020/1/17 16:08
 **/
@Configuration
@EnableConfigurationProperties(GuavaCacheProperties.class)
public class GuavaConfig {


    @Autowired
    private GuavaCacheProperties cacheProperties;

    @SuppressWarnings("all")
    @Bean
    public GuavaCacheUtil cacheUtil() {
        return new GuavaCacheUtil(new GuavaCacheLoader(),
                new GuavaWeigher()
                , new GuavaRemovalListener()
                , cacheProperties.getMaxWeight()
                , cacheProperties.getMaxSize()
                , cacheProperties.getRefreshTime()
                , TimeUnit.SECONDS
                , cacheProperties.getExpireTime()
                , TimeUnit.SECONDS
                , cacheProperties.getExpireMethod()
                , cacheProperties.getKeyReferenceMethod()
                , cacheProperties.getValueReferenceMethod());
    }
}
