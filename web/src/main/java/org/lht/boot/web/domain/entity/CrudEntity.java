package org.lht.boot.web.domain.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author LiHaitao
 * @description CrudEntity:
 * @date 2020/1/2 15:42
 **/
@Data
public abstract class CrudEntity<PK> implements Entity<PK> {

    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status;

    @TableField(value = "create_code", fill = FieldFill.INSERT)
    private String creatorCode;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    @TableField(value = "update_code", fill = FieldFill.UPDATE)
    private String updaterCode;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Long updateTime;

}
