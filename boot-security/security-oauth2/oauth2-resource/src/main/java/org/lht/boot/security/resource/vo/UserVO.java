package org.lht.boot.security.resource.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.web.domain.vo.AbstractVO;

import java.util.Set;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/25 8:03 PM
 **/
@Data
@ApiModel("用户VO")
public class UserVO extends AbstractVO<UserInfo, Integer> {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("身份证")
    private String identify;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 转换中文前端展示
     * @param sex
     */
    private void setSex(Integer sex) {
        this.sex = sex == 1 ? "男" : "女";
    }

    /**
     * 角色信息
     */
    private Set<RoleVO> roles;

}
