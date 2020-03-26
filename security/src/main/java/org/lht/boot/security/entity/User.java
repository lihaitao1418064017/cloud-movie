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
 * @description User:
 * @date 2020/3/18 11:23
 **/
@Data
@TableName("user")
@ApiModel("用户实体")
public class User extends BaseCrudEntity<Integer> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户姓名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}
