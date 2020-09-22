package org.hhy.cloud.crawl.service;

import org.hhy.cloud.crawl.entity.Job;
import org.lht.boot.web.service.BaseCrudService;

/**
 * @Classname JobService
 * @Description JobService
 * @Date 2020/9/19 11:48 PM
 * @Created by yupeng
 */
public interface JobService extends BaseCrudService<Job, String> {

    /**
     * 根据id更新job状态
     *
     * @param id
     * @param status
     * @return
     */
    String updateJobStatus(String id, Integer status);

    String updateSuccess(String id);

    String updateFail(String id);

    String updateRunning(String id);


}
