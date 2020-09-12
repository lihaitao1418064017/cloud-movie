package org.hhy.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

/**
 * Description: 测试job
 *
 * @Author lht
 * @Date 2020/9/12 下午1:54
 **/

@Component
public class TestJob extends IJobHandler {


    @XxlJob("test_job")
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("ssssssssssss");
        return ReturnT.SUCCESS;
    }


}
