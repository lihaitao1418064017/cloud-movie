package org.lht.boot.security.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.lht.boot.security.entity.Role;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/25 8:01 PM
 **/
@Data
@ApiModel("角色VO")
public class RoleVO extends AbstractVO<Role,Integer> {


    @TableId(type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty(name = "角色名称")
    private String name;
}
