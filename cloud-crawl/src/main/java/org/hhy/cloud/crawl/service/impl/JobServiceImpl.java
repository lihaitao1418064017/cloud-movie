package org.hhy.cloud.crawl.service.impl;

import org.hhy.cloud.crawl.dao.JobDao;
import org.hhy.cloud.crawl.entity.Job;
import org.hhy.cloud.crawl.entity.enums.JobStatusEnums;
import org.hhy.cloud.crawl.service.JobService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Classname JobServiceImpl
 * @Description job实现类
 * @Date 2020/9/19 11:49 PM
 * @Created by yupeng
 */
@Service
public class JobServiceImpl extends BaseMybatisCrudServiceImpl<Job, String, JobDao> implements JobService {


    @Override
    public String updateJobStatus(String id, Integer status) {
        Job job = new Job();
        job.setId(id);
        job.setStatus(status);
        return patch(job);
    }

    @Override
    public String updateSuccess(String id) {
        return updateJobStatus(id, JobStatusEnums.SUCCESS.getCode());
    }

    @Override
    public String updateFail(String id) {
        return updateJobStatus(id, JobStatusEnums.ERROR.getCode());
    }

    @Override
    public String updateRunning(String id) {
        return updateJobStatus(id, JobStatusEnums.RUNNING.getCode());

    }


}
