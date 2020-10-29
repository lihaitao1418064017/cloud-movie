package org.hhy.cloud.crawl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hhy.cloud.crawl.entity.CustomSpider;
import org.hhy.cloud.crawl.pipeline.CustomPipeline;
import org.hhy.cloud.crawl.pipeline.CustomProcessor;
import org.hhy.cloud.crawl.service.JobService;
import org.hhy.cloud.crawl.service.SpiderControlService;
import org.hhy.cloud.crawl.service.SpiderService;
import org.hhy.cloud.crawl.utils.DefaultHttpClientDownloader;
import org.hhy.cloud.crawl.vo.JobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LiHaitao
 * @description 爬虫任务实现
 * @date 2020/9/22 10:58
 **/
@Slf4j
@Service
public class SpiderControlServiceImpl implements SpiderControlService {

    @Autowired
    private SpiderService spiderService;


    @Autowired
    private JobService jobService;

    @Autowired
    private CustomProcessor customProcessor;

    @Autowired
    private CustomPipeline customPipeline;


    /**
     * 运行爬虫任务线程池
     * 通过这种方式创建线程池：ThreadFactoryBuilder
     * 定义线程的名称
     */
    private ExecutorService spiderExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2
            , Runtime.getRuntime().availableProcessors() * 4
            , 0
            , TimeUnit.SECONDS
            , new LinkedBlockingQueue<>()
            , new ThreadFactoryBuilder().setNameFormat("spider-pool-%d").build()
            , new ThreadPoolExecutor.AbortPolicy());


    @Override
    public void startSpider(JobVO job) {
        // 更新任务状态-运行中
        jobService.updateRunning(job.getId());
        // 根据Job.getSpiderId获取爬虫配置
        CustomSpider customSpider = this.buildCustomSpider(job);
        // 解析内容
        customProcessor.setCustomSpider(customSpider);
        // 初始化构建爬虫
        Spider spider = buildContentWorker(customSpider, job, customProcessor);

        // 启动爬虫
        try {
            spider.run();
        } catch (Exception e) {
            log.info("爬虫执行异常，任务id：{}, error:{}", job.getId(), e.getMessage());
            jobService.updateFail(job.getId());
            return;
        }
        jobService.updateSuccess(job.getId());
    }

    @Override
    public void startSpider(List<JobVO> jobs) {
        jobs.forEach(job -> {
            spiderExecutor.execute(() -> {
                startSpider(job);
            });
        });
    }

    /**
     * 构建爬虫Spider
     *
     * @param job 爬虫任务
     * @return CustomSpider
     */
    private CustomSpider buildCustomSpider(JobVO job) {
        CustomSpider customSpider = new CustomSpider();
        BeanUtil.copyProperties(job, customSpider);
        customSpider.setPages(job.getPages());
        return customSpider;
    }


    /**
     * 构建Spider任务
     *
     * @param spider
     * @param job
     * @param processor
     * @return
     */
    private Spider buildContentWorker(CustomSpider spider, JobVO job, CustomProcessor processor) {
        Spider contentWorker = Spider.create(processor);
        contentWorker.setDownloader(new DefaultHttpClientDownloader());
        contentWorker.thread(spider.getThreadNum() == null ? 1 : spider.getThreadNum())
                .setUUID(job.getId())
                .addUrl(spider.getUrl())
                .addPipeline(customPipeline);
        contentWorker.setExitWhenComplete(false);
        return contentWorker;
    }


}
