package org.lht.boot.security.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @author LiHaitao
 * @description Permission: 权限
 * @date 2020/3/18 11:26
 **/
@Data
@TableName("permission")
@ApiModel("权限实体")
public class Permission extends BaseCrudEntity<Integer> {


    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 访问url
     */
    private String url;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String sign;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 上级权限
     */
    private Integer pid;

    /**
     * 权限路径
     */
    private String path;

}
