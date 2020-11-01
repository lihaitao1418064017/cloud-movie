package org.hhy.cloud.crawl.pipeline.consumer.elasticsearch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static org.hhy.cloud.crawl.constant.CrawlConstant.CRAWL_TOPIC;

/**
 * @author LiHaitao
 * @description 消费爬虫内容到elasticsearch
 * @date 2020/9/22 13:51
 **/
@Component
public class ElasticSearchPipelineConsumer {


//    @KafkaListener(topics = {CRAWL_TOPIC})
    public void listener(String message) {


    }
}
