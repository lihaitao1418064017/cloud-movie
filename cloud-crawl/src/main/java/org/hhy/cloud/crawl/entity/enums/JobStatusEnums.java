package org.hhy.cloud.crawl.entity.enums;

import lombok.Getter;

/**
 * @Classname JobStatusEnums
 * @Description job状态码
 * @Date 2020/9/19 11:27 PM
 * @Created by yupeng
 */
@Getter
public enum JobStatusEnums {


    /**
     * 可运行
     */
    WAIT(0),
    /**
     * 排队中
     */
    QUEUING(1),
    /**
     * 执行成功
     */
    SUCCESS(2),
    /**
     * 运行中
     */
    RUNNING(3),
    /**
     * 阻塞
     */
    BLOCKED(4),
    /**
     * 暂停
     */
    PAUSED(5),
    /**
     * 执行失败
     */
    ERROR(6),
    /**
     * 结束
     */
    DEAD(7);


    private final Integer code;

    JobStatusEnums(Integer code) {
        this.code = code;
    }

}
