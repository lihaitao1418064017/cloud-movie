package org.hhy.cloud.crawl.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @Classname TemplatePage
 * @Description 页面解析规则
 * @Date 2020/9/17 11:13 PM
 * @Created by yupeng
 */
@Data
@TableName("template_page")
public class TemplatePage extends BaseCrudEntity<String> {


    private String id;

    /**
     * 任务id
     */
    private String jobId;
    /**
     * 页面名称
     * eg: 列表页，详情页
     */
    private String name;

    /**
     * url 正则
     */
    private String urlRegex;

}
