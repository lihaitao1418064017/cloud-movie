package org.lht.boot.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @author LiHaitao
 * @description Permission:
 * @date 2020/3/18 11:26
 **/
@Data
@TableName("permission")
public class Permission extends BaseCrudEntity<Integer> {


    @TableId(type = IdType.AUTO)
    private Integer id;

    private String url;

    private String name;

    private String description;

    private Integer pid;

}
