package org.hhy.cloud.crawl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LiHaitao
 * @description 爬虫配置属性
 * @date 2020/9/22 11:22
 **/
@Data
@ConfigurationProperties(prefix = "cloud.crawl")
public class CrawlProperties {

    /**
     * 代理
     */
    private String siteUserAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";


}
