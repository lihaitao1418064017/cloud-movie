package org.hhy.cloud.crawl.vo;

import lombok.Data;
import org.hhy.cloud.crawl.entity.Job;
import org.lht.boot.web.domain.vo.AbstractVO;

import java.time.LocalDateTime;

/**
 * @Classname Job
 * @Description 爬虫任务
 * @Date 2020/9/19 11:03 PM
 * @Created by yupeng
 */
@Data
public class JobVO extends AbstractVO<Job, String> {


    private String id;

    /**
     * 爬虫id
     */
    private String spiderId;

    /**
     * 爬虫名称
     */
    private String spiderName;

    /**
     * 排序
     */
    private Integer sortIndex;

    /**
     * 入口页urL
     */
    private String url;

    /**
     * 动态字段
     */
    private TemplatePageVO pages;

    /**
     * 线程数
     */
    private Integer threadNum;

    /**
     * 每个页面处理完后的睡眠时间 单位秒
     */
    private Integer sleepTime;

    /**
     * 页面下载失败重试次数
     */
    private Integer retryTimes;

    /**
     * 重试睡眠时间 单位秒
     */
    private Integer retrySleepTime;

    /**
     * 页面爬取失败后放回队列的次数
     */
    private Integer cycleRetryTimes;

    /**
     * 下载页面超时时间 单位秒
     */
    private Integer timeOut;
    /**
     * 完成时间
     */
    private LocalDateTime endTime;

    /**
     * 爬取数量
     */
    private Long successNum;

}
