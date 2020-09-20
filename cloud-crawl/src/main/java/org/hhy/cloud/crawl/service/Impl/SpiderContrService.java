package org.hhy.cloud.crawl.service.Impl;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.assertj.core.util.Lists;
import org.hhy.cloud.crawl.config.SpiderHolder;
import org.hhy.cloud.crawl.constant.JobStatus;
import org.hhy.cloud.crawl.entity.Job;
import org.hhy.cloud.crawl.entity.MySpider;
import org.hhy.cloud.crawl.entity.TemplateField;
import org.hhy.cloud.crawl.entity.TemplatePage;
import org.hhy.cloud.crawl.pipeline.ListPatternProcessor;
import org.hhy.cloud.crawl.service.JobService;
import org.hhy.cloud.crawl.service.SpiderService;
import org.hhy.cloud.crawl.utils.DefaultHttpClientDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Classname SpiderContrService
 * @Description 爬虫控制类
 * @Date 2020/9/19 11:50 PM
 * @Created by yupeng
 */

@Service
@Scope(value = "singleton")
public class SpiderContrService extends Thread{


    @Autowired
    private SpiderService spiderService;


    @Autowired
    private JobService jobService;


    /**
     * 运行爬虫任务线程池
     * 通过这种方式创建线程池：ThreadFactoryBuilder
     * 定义线程的名称
     */
    private ExecutorService spiderExecutor = new ThreadPoolExecutor(8, 8, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("spider-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());


    @Override
    public void run() {
        while (true) {
            // 初始化任务

            // 获取状态为等待的所有任务 todo
            List<Job> jobList = Lists.newArrayList();
            jobList.forEach(each -> {
                each.setStatus(JobStatus.QUEUING);
                each.setCreateTime(System.currentTimeMillis());
                each.setUpdateTime(System.currentTimeMillis());
                // 更新 任务状态为-排队中
                jobService.updateJobStatus(each.getId(),JobStatus.QUEUING);

                // 处理任务
                spiderExecutor.submit(() -> startTask(each));
            });

        }
    }

    private void startTask(final Job job) {
        // 更新任务状态-运行中
        jobService.updateJobStatus(job.getId(),JobStatus.QUEUING);

        // 根据Job.getSpiderId获取爬虫配置

        MySpider spider = new MySpider();




        // 初始化解析字段
        initFiles(spider);
        ListPatternProcessor processor = new ListPatternProcessor();
        // 解析内容
        processor.setmySpider(spider);

        Map<String/*内容页链接*/, LinkedHashMap<String, String> /*列表页提取的数据*/> itemHolder = new ConcurrentHashMap<>();

        // 初始化构建爬虫
        Spider spider1 = buildContentWorker(spider, job, itemHolder, processor);

        // 放入爬虫管理器，用于控制爬虫
        SpiderHolder.putSpider(spider1);


        // 启动爬虫
        spider1.runAsync();


        SpiderHolder.removeSpider(spider1.getUUID());

        finishJob(job);
    }


    private void finishJob(Job job) {


        // 更新任务状态，数量，

    }



    private Spider buildContentWorker(MySpider spider, Job job, Map<String, LinkedHashMap<String, String>> itemHolder, ListPatternProcessor processor) {
        Spider contentWorker = Spider.create(processor);
        contentWorker.setDownloader(new DefaultHttpClientDownloader());
        contentWorker.thread(spider.getThreadNum() == null ? 1 : spider.getThreadNum())
                .setUUID(job.getId())
                .addPipeline((resultItems, task) -> {
                    String requestUrl = resultItems.getRequest().getUrl();
                    Map<String, Object> resultMap = resultItems.getAll();
                    //合并数据
                    LinkedHashMap<String, String> item = itemHolder.get(requestUrl);
                    //把暂存的数据删了 防止内存溢出
                    itemHolder.remove(requestUrl);

                    resultMap.forEach((k, v) -> item.put(k, ((List) v).get(0).toString()));
                    //保存
                    final List<LinkedHashMap<String, String>> resultList = new ArrayList<>();
                    resultList.add(item);

                    // 保存数据



                });
        contentWorker.setExitWhenComplete(false);
        return contentWorker;
    }




    private void initFiles(MySpider spider) {
        Map<String, String> filds = Maps.newHashMap();

        List<TemplatePage> pages = spider.getPages();


        pages.forEach(page -> {
            List<TemplateField> fields = page.getFields();
            fields.forEach(field -> {
                filds.put(field.getName(),field.getXpathRule());
            });
        });
        spider.setFields(filds);
    }
}
