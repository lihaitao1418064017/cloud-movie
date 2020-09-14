package org.hhy.xxl.job.executor.consumer.config;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.Map;

/**
 * @author LiHaitao
 * @description kafka相关配置
 * @date 2020/9/14 15:18
 **/
@Configuration
@EnableConfigurationProperties(CloudCrawlKafkaProperties.class)
public class KafkaConfig {


    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired
    private CloudCrawlKafkaProperties cloudCrawlKafkaProperties;

    /**
     * 消费者问题：
     * 1，消息丢失：
     * <p>
     * request.required.acks：
     * 0：只由leader处理，没有同步道。延迟小，可靠性差
     * 1：leader挂掉后选举新的leader，保证一定的数据可靠性与延迟性
     * -1：需要设置min.insync.replicas=设置ISR列表最小副本数默认为1，leader转发道所有的ISR中；
     * 延迟高，有一个副本存活就不会丢失消息
     * <p>
     * <p>
     * 解决方案：
     * 生产者设置：
     * log.flush.interval.messages=10 内存中达到10个消息后写入磁盘
     * log.flush.interval.ms=1000  不经过一秒后写入磁盘
     * request.required.acks=-1 ack机制
     * 消费者设置
     * enable.auto.commit=false 自动提交策略，设置不自动提交，由业务本身维护
     * 2，重复消费
     * enable.auto.commit=false
     * enable.idempotence=true kafka幂等性
     */

    /**
     * 配置 cloud-crawl 消费工厂
     *
     * @return 监听工厂
     */
    @Bean("cloudMovieSingleFactory")
    public KafkaListenerContainerFactory<?> singleFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        Map<String, Object> stringObjectMap = kafkaProperties.buildConsumerProperties();
        stringObjectMap.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, Lists.newArrayList(cloudCrawlKafkaProperties.getBootstrapServers()));
        stringObjectMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, cloudCrawlKafkaProperties.getEnableAutoCommit());
        stringObjectMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, cloudCrawlKafkaProperties.getAutoOffsetReset());
        stringObjectMap.put(ConsumerConfig.GROUP_ID_CONFIG, cloudCrawlKafkaProperties.getGroupId());

        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(stringObjectMap);
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        /**
         * AckMode针对ENABLE_AUTO_COMMIT_CONFIG=false时生效，有以下几种：
         *
         * RECORD
         * 每处理一条commit一次
         *
         * BATCH(默认)
         * 每次poll的时候批量提交一次，频率取决于每次poll的调用频率
         *
         * TIME
         * 每次间隔ackTime的时间去commit(跟auto commit interval有什么区别呢？)
         *
         * COUNT
         * 累积达到ackCount次的ack去commit
         *
         * COUNT_TIME
         * ackTime或ackCount哪个条件先满足，就commit
         *
         * MANUAL
         * listener负责ack，但是背后也是批量上去
         *
         * MANUAL_IMMEDIATE
         * listner负责ack，每调用一次，就立即commit
         *
         */
        //设置提交偏移量的方式
        factory.getContainerProperties().
                setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }
}
