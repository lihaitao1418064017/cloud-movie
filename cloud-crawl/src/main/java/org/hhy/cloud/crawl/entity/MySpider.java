package org.hhy.cloud.crawl.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;
import java.util.Map;

/**
 * @Classname MySpider
 * @Description 爬虫类
 * @Date 2020/9/14 11:26 PM
 * @Created by yupeng
 */
@Data
public class MySpider {


    @ApiModelProperty(notes = "爬虫ID")
    private String id;

    @ApiModelProperty(notes = "爬虫名")
    private String name;

    @ApiModelProperty(notes = "排序")
    private Integer sortIndex;

    @ApiModelProperty(notes = "入口页urL")
    private String url;

    @ApiModelProperty(notes = "动态字段")
    private List<TemplatePage> pages;

    // 爬虫设置
    @ApiModelProperty(notes = "线程数")
    private Integer threadNum;

    @ApiModelProperty(notes = "每个页面处理完后的睡眠时间 单位秒")
    private Integer sleepTime;

    @ApiModelProperty(notes = "页面下载失败重试次数")
    private Integer retryTimes;

    @ApiModelProperty(notes = "重试睡眠时间 单位秒")
    private Integer retrySleepTime;

    @ApiModelProperty(notes = "页面爬取失败后放回队列的次数")
    private Integer cycleRetryTimes;

    @ApiModelProperty(notes = "下载页面超时时间 单位秒")
    private Integer timeOut;


    @Transient
    private Map<String/*字段名*/, String/*xpath*/> fields;

}
