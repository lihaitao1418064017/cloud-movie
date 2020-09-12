package org.hhy.cloud.movie.vo;

import lombok.Data;
import org.hhy.cloud.movie.entity.Movie;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * Description: 电影
 *
 * @Author lht
 * @Date 2020/9/12 下午10:28
 **/
@Data
public class MovieVO extends AbstractVO<Movie,String> {

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
    private Integer score;

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


    private String extentField;
}
