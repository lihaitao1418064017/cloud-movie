package org.lht.boot.security.resource.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/25 8:04 PM
 **/
@Data
@ApiModel("权限VO")
public class PermissionVO extends AbstractVO<Permission, Integer> {


    @ApiModelProperty("权限url")
    private String url;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限描述")
    private String description;

    @ApiModelProperty("上级权限id")
    private Integer pid;
}
