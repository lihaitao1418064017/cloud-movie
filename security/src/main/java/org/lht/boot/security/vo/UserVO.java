package org.lht.boot.security.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.lht.boot.security.entity.User;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/25 8:03 PM
 **/
@Data
@ApiModel("用户VO")
public class UserVO extends AbstractVO<User,Integer> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(name = "用户姓名")
    private String username;

    @ApiModelProperty(name = "密码")
    private String password;
}
