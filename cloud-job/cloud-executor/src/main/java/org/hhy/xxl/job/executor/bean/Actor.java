package org.hhy.xxl.job.executor.bean;

import lombok.Data;
import lombok.ToString;
import org.hhy.xxl.crud.annotation.ID;
import org.hhy.xxl.crud.annotation.Table;
import org.hhy.xxl.crud.annotation.TableField;

@Data
@ToString
@Table("actor")
public class Actor extends BaseEntity<String> {

    @ID
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 地区
     */
    private String area;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 出生年月
     */
    private String birthday;

    /**
     * 职业 kafka ,es , redis , mysql
     */
    @TableField("vocational")
    private String vocational;


}