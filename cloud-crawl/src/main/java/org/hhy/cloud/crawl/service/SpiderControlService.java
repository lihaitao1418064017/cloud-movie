package org.hhy.cloud.crawl.service;

import org.hhy.cloud.crawl.vo.JobVO;

import java.util.List;

/**
 * @author by yupeng
 * @Classname SpiderContrService
 * @Description 爬虫控制类
 * @Date 2020/9/19 11:50 PM
 */
public interface SpiderControlService {


    /**
     * 开始爬虫
     *
     * @param job 爬虫任务
     */
    void startSpider(JobVO job);


    /**
     * 批量开始爬虫
     *
     * @param jobs 爬虫任务
     */
    void startSpider(List<JobVO> jobs);
}
