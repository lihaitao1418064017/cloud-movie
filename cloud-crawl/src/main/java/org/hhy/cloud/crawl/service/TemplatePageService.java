package org.hhy.cloud.crawl.service;

import org.hhy.cloud.crawl.entity.TemplatePage;
import org.hhy.cloud.crawl.vo.TemplatePageVO;
import org.lht.boot.web.service.BaseCrudService;

/**
 * @description: 模板页service
 * @author: LiHaitao
 * @date: 2020/9/22 17:13
 */
public interface TemplatePageService extends BaseCrudService<TemplatePage, String> {


    /**
     * 查询页面和页面字段
     *
     * @param jobId
     * @return
     */
    TemplatePageVO selectTemplatePageVO(String jobId);
}
