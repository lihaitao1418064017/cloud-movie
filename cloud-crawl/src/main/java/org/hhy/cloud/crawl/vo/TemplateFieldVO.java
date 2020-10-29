package org.hhy.cloud.crawl.vo;

import lombok.Data;
import org.hhy.cloud.crawl.entity.TemplateField;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * @Classname TemplateFieldVO
 * @Description 动态字段
 * @Date 2020/9/14 11:37 PM
 * @Created by yupeng
 */
@Data
public class TemplateFieldVO extends AbstractVO<TemplateField, String> {


    /**
     * 页面id
     */
    private String pageId;

    /**
     * 列表字段还是详情页字段
     * 0:detail
     * 1:list
     */
    private Integer detailOrList;
    /**
     * 字段名
     */
    private String name;

    /**
     * 解析规则
     */
    private String xpathRule;
    /**
     * 正则
     */
    private String regex;

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
