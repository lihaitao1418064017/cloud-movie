package org.hhy.cloud.crawl.service.impl;

import org.hhy.cloud.crawl.dao.TemplatePageDao;
import org.hhy.cloud.crawl.entity.TemplatePage;
import org.hhy.cloud.crawl.service.TemplatePageService;
import org.hhy.cloud.crawl.vo.TemplatePageVO;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 模板页
 * @author: LiHaitao
 * @date: 2020/9/22 17:14
 */
@Service
public class TemplatePageServiceImpl extends BaseMybatisCrudServiceImpl<TemplatePage, String, TemplatePageDao> implements TemplatePageService {


    @Override
    public TemplatePageVO selectTemplatePageVO(String jobId) {
        return dao.selectTemplatePageVO(jobId);
    }
}
