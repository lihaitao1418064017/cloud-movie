package org.hhy.cloud.crawl.entity;

/**
 * @Classname TemplatePage
 * @Description 页面解析规则
 * @Date 2020/9/17 11:13 PM
 * @Created by yupeng
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 页面信息
 */
@Data
public class TemplatePage {


    /**
     * 页面名称
     * eg: 列表页，详情页
     */
    private String name;

    /**
     * url 正则
     */
    private String urlRegex;

    @ApiModelProperty(notes = "动态字段")
    private List<TemplateField> fields;
}
