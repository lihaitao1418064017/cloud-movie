package org.hhy.cloud.crawl.pipeline.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.lht.boot.mq.kafka.producer.KafkaProducerListener;

/**
 * @description: 爬虫生产者生产监听
 * @author: LiHaitao
 * @date: 2020/9/22 14:06
 */
@Slf4j
public class CrawlProducerListener extends KafkaProducerListener<String, String> {


    @Override
    public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
        log.info("Send successfully [topic:{},partition:{},key:{},value:{},recordMetadata:{}]", topic, partition, key, value, recordMetadata);
    }

    @Override
    public void onError(String topic, Integer partition, String key, String value, Exception exception) {
        log.error("Send error [topic:{},partition:{},key:{},value:{},exception:{}]", topic, partition, key, value, exception);

    }


}
