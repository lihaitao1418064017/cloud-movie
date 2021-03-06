package org.lht.boot.web.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiHaitao
 * @description User:
 * @date 2020/1/2 17:08
 **/
@Data
@TableName("user")
public class User extends BaseCrudEntity<String> {

    @TableId(type = IdType.UUID)
    private String id;

    private String name;

    private Integer age;


}
