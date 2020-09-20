package org.hhy.cloud.crawl.constant;

/**
 * @Classname JobStatusEnums
 * @Description TODO
 * @Date 2020/9/19 11:37 PM
 * @Created by yupeng
 */
public class JobStatus {


    public static final String WAIT = "wait";//待执行

    public static final String QUEUING = "queuing";//排队中

    public static final String RUNING = "running";//执行中

    public static final String SUCCESS = "success";//执行成功

    public static final String PAUSED = "paused";//暂停

    public static final String ERROR = "error";//执行失败

}
