package org.lht.boot.security.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @author LiHaitao
 * @description Permission: 权限
 * @date 2020/3/18 11:26
 **/
@Data
@TableName("permission")
@ApiModel("权限实体")
public class Permission extends BaseCrudEntity<Integer> {


    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("权限url")
    private String url;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限标识")
    private String sign;

    @ApiModelProperty("权限描述")
    private String description;

    @ApiModelProperty("上级权限id")
    private Integer pid;

}
