package org.hhy.xxl.job.executor.bean;


import lombok.Data;

@Data
public class Movie extends BaseEntity<String> {


    private String id;

    /**
     * 电影名
     */
    private String name;

    /**
     * 导演
     */
    private String director;


    /**
     * 简介
     */
    private String intro;

    /**
     * 评分
     */
    private Double score;

    /**
     * 国家或地区
     */
    private String area;

    /**
     * 播放时长
     */
    private Long playTimes;

    /**
     * 年份
     */
    private String year;

    /**
     * 编剧
     */
    private String scriptwriter;

    /**
     * 语言
     */
    private String language;

    /**
     * 别名
     */
    private String alias;

    /**
     * 播放地址
     */
    private String uri;
    
    private String extendField;

}