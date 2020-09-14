package org.hhy.xxl.job.executor.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author LiHaitao
 * @description
 * @date 2020/9/14 15:19
 **/
@Data
@ConfigurationProperties(prefix = "cloud.crawl.kafka")
public class CloudCrawlKafkaProperties {

    private List<String> bootstrapServers;

    private Boolean enableAutoCommit = false;
    /**
     * 当zookeeper中没有初始的offset时候的处理方式 。
     * earliest ：重置为最小值
     * latest:重置为最大值
     */
    private String autoOffsetReset = "latest";

    private String groupId = "cloud-crawl";

    private Long sessionTimeoutMs;


}

