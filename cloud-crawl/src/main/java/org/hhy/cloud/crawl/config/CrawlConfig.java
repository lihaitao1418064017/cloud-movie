package org.hhy.cloud.crawl.config;

import org.hhy.cloud.crawl.pipeline.CustomProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Site;

/**
 * @author LiHaitao
 * @description 爬虫相关配置
 * @date 2020/9/22 11:20
 **/
@Configuration
@EnableConfigurationProperties(CrawlProperties.class)
public class CrawlConfig {

    @Autowired
    private CrawlProperties crawlProperties;

    @Bean
    public CustomProcessor customProcessor() {
        return new CustomProcessor();
    }

    @Bean
    @ConditionalOnMissingBean(CustomProcessor.class)
    public Site site() {
        return Site
                .me()
                .setUserAgent(
                        crawlProperties.getSiteUserAgent());
    }

}
