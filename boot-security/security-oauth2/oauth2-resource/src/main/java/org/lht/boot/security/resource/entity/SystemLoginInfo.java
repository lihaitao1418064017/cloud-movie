package org.lht.boot.security.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

import java.util.Date;

/**
 * @description: 系统访问记录
 * @author: LiHaitao
 * @date: 2020/7/14 17:25
 */
@Data
@TableName("system_login_info")
public class SystemLoginInfo extends BaseCrudEntity<Integer> {


    @TableId(type = IdType.AUTO)
    private Integer id;
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
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date accessTime;


}