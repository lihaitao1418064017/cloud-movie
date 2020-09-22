package org.hhy.cloud.crawl.entity;

import lombok.Data;
import org.hhy.cloud.crawl.vo.TemplatePageVO;

/**
 * @Classname CustomSpider
 * @Description 爬虫类
 * @Date 2020/9/14 11:26 PM
 * @Created by yupeng
 */
@Data
public class CustomSpider {

    /**
     * 爬虫ID
     */
    private String id;

    /**
     * 爬虫名
     */
    private String name;

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

    // 爬虫设置
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


}
