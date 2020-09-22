package org.hhy.cloud.crawl.dao;

import org.hhy.cloud.crawl.entity.Job;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description 任务dao
 * @date 2020/9/22 15:54
 **/
@Repository
public interface JobDao extends BaseMybatisPlusDao<Job> {

}
