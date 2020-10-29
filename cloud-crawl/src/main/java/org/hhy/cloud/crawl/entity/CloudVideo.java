package org.hhy.cloud.crawl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @author LiHaitao
 * @description 视频信息, 此实体
 * 存储爬虫所能爬取的所有重点字段信息，从中提取信息提供给cloud-movie平台使用
 * @date 2020/10/29 20:02
 **/
@Data
@TableName("cloud_video")
public class CloudVideo extends BaseCrudEntity<String> {


    @TableId(type = IdType.AUTO)
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
    @TableField("play_times")
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


    @TableField("extend_field")
    private String extendField;
}
