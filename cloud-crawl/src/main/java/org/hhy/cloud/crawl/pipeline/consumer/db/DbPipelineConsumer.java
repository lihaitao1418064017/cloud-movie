package org.hhy.cloud.crawl.pipeline.consumer.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static org.hhy.cloud.crawl.constant.CrawlConstant.CRAWL_TOPIC;

/**
 * @author LiHaitao
 * @description 消费爬虫内容到elasticsearch
 * @date 2020/9/22 13:51
 **/
@Slf4j
@Component
public class DbPipelineConsumer {


    @KafkaListener(topics = {CRAWL_TOPIC})
    public void listener(String message) {


    }

}
