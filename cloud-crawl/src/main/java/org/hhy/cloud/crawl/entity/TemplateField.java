package org.hhy.cloud.crawl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @Classname TemplateFieldVO
 * @Description 动态字段
 * @Date 2020/9/14 11:37 PM
 * @Created by yupeng
 */
@Data
@TableName("template_field")
public class TemplateField extends BaseCrudEntity<String> {


    @TableId(type = IdType.AUTO)
    private String id;

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
     * 正则
     */
    private String regex;

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
