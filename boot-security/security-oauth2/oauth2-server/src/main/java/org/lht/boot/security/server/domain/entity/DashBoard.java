package org.lht.boot.security.server.domain.entity;

import lombok.Data;

/**
 * @author LiHaitao
 * @description 主页信息
 * @date 2020/9/3 16:08
 **/
@Data
public class DashBoard {

    /**
     * 上次登录时间
     */
    private String lastLoginTime;

    /**
     * 上次登录地点
     */
    private String lastLoginAddress;

    /**
     * 用户访问量次
     */
    private String loginNumber;

    /**
     * 系统消息总数
     */
    private String totalMessage;

    /**
     * 用户总数
     */
    private Long totalUser;


}
