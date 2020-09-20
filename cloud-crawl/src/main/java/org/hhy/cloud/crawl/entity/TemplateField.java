package org.hhy.cloud.crawl.entity;

import lombok.Data;

/**
 * @Classname TemplateField
 * @Description 动态字段
 * @Date 2020/9/14 11:37 PM
 * @Created by yupeng
 */

@Data
public class TemplateField {

    /**
     * 字段名
     */
    private String name;


    /**
     * 解析规则
     */
    private String xpathRule;

    /**
     * 排序
     */
    private Integer sortIndex;

    /**
     * 类型
     * eg: 文本；文件
     */
    private String type;


}
