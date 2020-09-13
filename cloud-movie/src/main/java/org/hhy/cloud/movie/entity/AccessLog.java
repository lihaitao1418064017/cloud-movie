package org.hhy.cloud.movie.entity;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.domain.entity.BaseCrudEntity;
import org.lht.boot.web.domain.entity.EsEntity;

import java.util.Date;

/**
 * @author LiHaitao
 * @description 访问日志
 * @date 2020/7/13 14:23
 **/
@Data
@EsEntity(index = "access_log", type = "access_log", alias = "access_log")
public class AccessLog extends BaseCrudEntity<String> {

    private String id = RandomUtil.simpleUUID();

    /**
     * 访问的操作
     */
    private String action;

    /**
     * 访问的操作
     *
     * @see AccessLogger#value()
     */
    private String module;

    /**
     * 描述
     *
     * @see AccessLogger#describe() ()
     */
    private String describe;

    /**
     * 请求者ip地址
     */
    private String ip;

    /**
     * 请求的url地址
     */
    private String url;

    /**
     * 请求时间戳
     *
     * @see System#currentTimeMillis()
     */
    private long requestTime;

    /**
     * 响应时间戳
     *
     * @see System#currentTimeMillis()
     */
    private long responseTime;

    /**
     * 异常信息,请求对应方法抛出的异常
     */
    private String exception;

    //    /**
    //     * 当前用户
    //     */
    private String username;


    private String params;

    private Date intoDate = new Date();
}
