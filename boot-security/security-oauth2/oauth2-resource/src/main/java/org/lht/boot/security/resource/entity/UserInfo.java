package org.lht.boot.security.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @author LiHaitao
 * @description UserInfo:用户基本信息
 * @date 2020/3/18 11:23
 **/
@Data
@TableName("user")
@ApiModel("用户实体")
public class UserInfo extends BaseCrudEntity<Integer> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证
     */
    private String identify;

    /**
     * 性别：0：男 ，1：女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 密码
     */
    private String password;

}
