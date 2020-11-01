package org.hhy.cloud.crawl.controller;

import org.hhy.cloud.crawl.entity.Job;
import org.hhy.cloud.crawl.service.JobService;
import org.hhy.cloud.crawl.vo.JobVO;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 爬虫任务
 *
 * @Author lht
 * @Date 2020/10/31 12:05 PM
 **/
@RestController
@RequestMapping("/job")
public class JobController  extends AbstractController<Job,String, JobVO, JobService> {
}
