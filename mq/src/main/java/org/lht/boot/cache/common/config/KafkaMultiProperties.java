package org.lht.boot.cache.common.config;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author LiHaitao
 * @description KafkaMultiProperties:
 * @date 2020/1/16 15:42
 **/
@Configuration
@ConfigurationProperties(value = "kafka.multi")
@Data
public class KafkaMultiProperties {

    private Integer concurrency;

    private List<String> bootstrapServers = Lists.newArrayList("localhost:9092");

    private String sessionTimeoutMs = "60000";

    private Integer maxPollRecords = 1000;

    private Boolean enableAutoCommit = true;

    private String autoOffsetReset = "earliest";

    private String groupId = "g_k";

    private String topic;


}
