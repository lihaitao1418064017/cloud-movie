package org.hhy.cloud.crawl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @Classname Job
 * @Description 爬虫任务
 * @Date 2020/9/19 11:03 PM
 * @Created by yupeng
 */
@Data
@TableName("job")
public class Job extends BaseCrudEntity<String> {


    @TableId(type = IdType.UUID)
    private String id;


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
    private Long endTime;

    /**
     * 爬取数量
     */
    @TableField(exist = false)
    private Long successNum;


}
