package org.lht.boot.security.server.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.searchbox.annotations.JestId;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;
import org.lht.boot.web.domain.entity.EsEntity;

import java.util.Date;

/**
 * @description: 系统访问记录
 * @author: LiHaitao
 * @date: 2020/7/14 17:25
 */
@Data
@EsEntity(index = "system_login_info", type = "system_login_info", alias = "system_login_info")
public class SystemLoginInfo extends BaseCrudEntity<String> {


    @JestId
    private String id;
    /**
     * 用户账号
     */
    private String username;


    /**
     * 地址
     */
    private String ipAddress;

    /**
     * 描述
     */
    private String msg;

    /**
     * 登录地点
     */
    private String address;

    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date accessTime;


}