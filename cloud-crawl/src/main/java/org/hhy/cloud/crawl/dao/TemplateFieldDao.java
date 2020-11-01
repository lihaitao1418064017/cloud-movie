package org.hhy.cloud.crawl.dao;

import org.hhy.cloud.crawl.entity.TemplateField;
import org.hhy.cloud.crawl.vo.TemplateFieldVO;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @description: 模板字段dao
 * @author: LiHaitao
 * @date: 2020/9/22 17:21
 */
@Repository
public interface TemplateFieldDao extends BaseMybatisPlusDao<TemplateField> {

    List<TemplateFieldVO> selectFieldsByPageId(String pageId);

}
