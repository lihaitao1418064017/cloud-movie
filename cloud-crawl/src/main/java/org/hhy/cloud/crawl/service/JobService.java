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
     * @param id     jobId
     * @param status 要更新的状态
     * @return
     */
    String updateJobStatus(String id, Integer status);

    /**
     * 更新成功状态
     *
     * @param id jobId
     * @return
     */
    String updateSuccess(String id);

    /**
     * 更新失败状态
     *
     * @param id jobId
     * @return
     */
    String updateFail(String id);

    /**
     * 更新running状态
     *
     * @param id jobId
     * @return
     */
    String updateRunning(String id);


}
