package org.hhy.cloud.crawl.vo;


import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import org.hhy.cloud.crawl.entity.TemplateField;
import org.hhy.cloud.crawl.entity.TemplatePage;
import org.lht.boot.web.domain.vo.AbstractVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 模板页VO
 * @author: LiHaitao
 * @date: 2020/9/22 17:35
 */
@Data
public class TemplatePageVO extends AbstractVO<TemplatePage, String> {


    /**
     * 任务id
     */
    private String jobId;
    /**
     * 页面名称
     * eg: 列表页，详情页
     */
    private String name;

    private String url;

    /**
     * url 正则
     */
    private String urlRegex;

    /**
     * xpath,用来获取列表页的列表长度和获取列表页的key
     * 详情页的key为page.getRequest().getUrl();
     */
    private String keyXpath;

    /**
     * ,用来获取列表页的列表长度和获取列表页的key
     * 详情页的key为page.getRequest().getUrl();
     */
    private String keyRegex;

    /**
     * 详情页和模版页字段
     */
    private List<TemplateFieldVO> fields;

    /**
     * 列表页动态字段
     */
    private List<TemplateFieldVO> listFields;

    public List<TemplateFieldVO> getListFields(){
        return getFieldsByDetailsOrList(1);
    }
    /**
     * 详情页动态字段
     */
    private List<TemplateFieldVO> detailFields;

    public List<TemplateFieldVO> getDetailFields(){
        return getFieldsByDetailsOrList(0);
    }

    private List<TemplateFieldVO> getFieldsByDetailsOrList(int i) {
        if (CollectionUtil.isEmpty(fields)) {
            return Lists.newArrayList();
        }
        return fields
                .stream()
                .filter(templateFieldVO -> templateFieldVO.getDetailOrList().equals(i))
                .collect(Collectors.toList());
    }


    /**
     *
     */
    public TemplatePageVO(){
        this.detailFields=getDetailFields();
        this.listFields=getListFields();
    }
}
