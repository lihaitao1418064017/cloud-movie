package org.lht.boot.security.resource.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.lht.boot.security.resource.entity.RolePermission;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/25 8:04 PM
 **/
@Data
@ApiModel("角色权限VO")
public class RolePermissionVO extends AbstractVO<RolePermission, Integer> {


    @TableId(type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty("角色id")
    private Integer roleId;

    @ApiModelProperty("权限id")
    private Integer permissionId;
}
