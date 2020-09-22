package org.hhy.cloud.crawl.dao;

import org.apache.ibatis.annotations.Param;
import org.hhy.cloud.crawl.entity.TemplatePage;
import org.hhy.cloud.crawl.vo.TemplatePageVO;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;


/**
 * @description: 模板页dao
 * @author: LiHaitao
 * @date: 2020/9/22 17:21
 */
@Repository
public interface TemplatePageDao extends BaseMybatisPlusDao<TemplatePage> {


    /**
     * 查询页面和页面字段
     *
     * @param jobId
     * @return
     */
    TemplatePageVO selectTemplatePageVO(@Param("jobId") String jobId);
}
