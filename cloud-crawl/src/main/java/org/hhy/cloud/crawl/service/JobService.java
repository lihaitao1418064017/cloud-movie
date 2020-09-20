package org.hhy.cloud.crawl.service;

import org.springframework.stereotype.Service;

/**
 * @Classname JobService
 * @Description TODO
 * @Date 2020/9/19 11:48 PM
 * @Created by yupeng
 */

public interface JobService {

    int updateJobStatus (String id,String status);

}
