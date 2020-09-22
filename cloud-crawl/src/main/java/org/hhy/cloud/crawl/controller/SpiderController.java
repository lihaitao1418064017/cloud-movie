package org.hhy.cloud.crawl.controller;

import org.assertj.core.util.Lists;
import org.hhy.cloud.crawl.entity.Job;
import org.hhy.cloud.crawl.service.JobService;
import org.hhy.cloud.crawl.service.SpiderControlService;
import org.hhy.cloud.crawl.service.TemplatePageService;
import org.hhy.cloud.crawl.vo.JobVO;
import org.hhy.cloud.crawl.vo.TemplatePageVO;
import org.lht.boot.lang.util.R;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.api.param.TermEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname SpiderController
 * @Description TODO
 * @Date 2020/9/13 10:35 PM
 * @Created by yupeng
 */
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @Autowired
    private SpiderControlService spiderControlService;

    @Autowired
    private TemplatePageService templatePageService;

    @Autowired
    private JobService jobService;

    @GetMapping("/start/{jobId}")
    public R start(@PathVariable String jobId) {
        Job job = jobService.get(jobId);
        TemplatePageVO templatePageVO = templatePageService.selectTemplatePageVO(jobId);
        JobVO jobVO = new JobVO().covertToVO(job);
        jobVO.setPages(templatePageVO);
        spiderControlService.startSpider(jobVO);
        return R.ok();
    }


    @PostMapping("/start/batch")
    public R start(@RequestBody List<String> jobIds) {
        List<Job> jobs = jobService.select(QueryParam.build(Term.build("id", TermEnum.in, jobIds)));
        List<JobVO> jobVOS = Lists.newArrayList();
        jobs.forEach(job -> {
            TemplatePageVO templatePageVO = templatePageService.selectTemplatePageVO(job.getId());
            JobVO jobVO = new JobVO().covertToVO(job);
            jobVO.setPages(templatePageVO);
            jobVOS.add(jobVO);
        });
        spiderControlService.startSpider(jobVOS);
        return R.ok();
    }

}
