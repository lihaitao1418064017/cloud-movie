package org.hhy.cloud.crawl.service.impl;

import org.hhy.cloud.crawl.dao.TemplateFieldDao;
import org.hhy.cloud.crawl.entity.TemplateField;
import org.hhy.cloud.crawl.service.TemplateFieldService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 模板字段
 * @author: LiHaitao
 * @date: 2020/9/22 17:14
 */
@Service
public class TemplateFieldServiceImpl extends BaseMybatisCrudServiceImpl<TemplateField, String, TemplateFieldDao> implements TemplateFieldService {


}
