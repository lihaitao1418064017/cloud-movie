package org.hhy.cloud.crawl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hhy.cloud.crawl.constant.JobStatus;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

import java.time.LocalDateTime;

/**
 * @Classname Job
 * @Description 爬虫任务
 * @Date 2020/9/19 11:03 PM
 * @Created by yupeng
 */
@Data
public class Job extends BaseCrudEntity<String> {


    private String id;

    @ApiModelProperty(notes = "爬虫id")
    private String spiderId;

    @ApiModelProperty(notes = "爬虫名称")
    private String spiderName;


    @ApiModelProperty(notes = "完成时间")
    private LocalDateTime endTime;

    @TableField(exist = false)
    @ApiModelProperty(notes = "爬取数量")
    private Long successNum;

    @ApiModelProperty(notes = "状态")
    private String status;


    Job convertBySpider(MySpider spider) {
        Job job = new Job();
        job.setSpiderId(spider.getId());
        job.setSpiderName(spider.getName());
        job.setCreateTime(System.currentTimeMillis());
        job.setStatus(JobStatus.WAIT);
        return job;

    }

}
