package org.hhy.xxl.job.executor.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiHaitao
 * @description 消费爬虫业务视频结构化数据落库
 * @date 2020/9/14 15:13
 **/
@Slf4j
@Component
public class CloudCrawlKafkaConsumer {


    @KafkaListener
    public void listener(ConsumerRecord record, Consumer consumer) {

        Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();
        //自行维护偏移量
        currentOffset.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1));

        //业务实现


        try {
            // 立即提交
            consumer.commitSync(currentOffset);
        } catch (Exception e) {
            log.info("CloudCrawlKafkaConsumer consumer exception：{}", e);
        } finally {
            try {
                //提交失败，再次提交，最终尝试
                consumer.commitSync(currentOffset);
            } catch (Exception e) {
                log.info("retry CloudCrawlKafkaConsumer consumer exception：{}", e);
            } finally {

                //更新偏移量到数据库，防止下次重复消费seek(TopicPartition, long) 恢复上次消费的位置

                consumer.close();
            }
        }


    }

}
