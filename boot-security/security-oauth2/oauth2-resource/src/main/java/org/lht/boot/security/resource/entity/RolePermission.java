package org.lht.boot.security.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @author LiHaitao
 * @description RolePermission: 角色与权限关联
 * @date 2020/3/18 11:34
 **/
@Data
@TableName("role_permission")
@ApiModel("角色和权限实体")
public class RolePermission extends BaseCrudEntity<Integer> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer roleId;

    private Integer permissionId;
}
