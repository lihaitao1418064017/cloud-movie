package org.lht.boot.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @author LiHaitao
 * @description RoleVO: 角色
 * @date 2020/3/18 11:25
 **/
@Data
@TableName("role")
@ApiModel("角色实体")
public class Role extends BaseCrudEntity<Integer> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色名称")
    private String name;
}
