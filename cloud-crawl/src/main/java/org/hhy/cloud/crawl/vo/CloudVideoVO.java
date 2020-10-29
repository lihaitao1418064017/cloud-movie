package org.hhy.cloud.crawl.vo;

import lombok.Data;
import org.hhy.cloud.crawl.entity.CloudVideo;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * @author LiHaitao
 * @description 视频VO
 * @date 2020/10/29 20:09
 **/
@Data
public class CloudVideoVO extends AbstractVO<CloudVideo, String> {

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
