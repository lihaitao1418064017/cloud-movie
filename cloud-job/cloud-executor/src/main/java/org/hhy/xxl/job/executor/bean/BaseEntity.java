package org.hhy.xxl.job.executor.bean;

import lombok.Data;
import org.hhy.xxl.crud.annotation.TableField;

@Data
public abstract class BaseEntity<PK> implements Entity<PK>{

    /**
     * 状态码，0无效，1有效
     */
    private Integer status;

    @TableField("creator_user")
    private String creatorUser;

    @TableField("create_time")
    private Long createTime;

    @TableField("update_user")
    private String updateUser;

    @TableField("update_time")
    private Long updateTime;

}