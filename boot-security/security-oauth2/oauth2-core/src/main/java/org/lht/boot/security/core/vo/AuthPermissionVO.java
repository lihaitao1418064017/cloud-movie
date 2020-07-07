package org.lht.boot.security.core.vo;


import lombok.Data;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/5/28 17:18
 */
@Data
public class AuthPermissionVO {

    /**
     * 主键id ，自增长
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 唯一标识（在系统中使用）
     */
    private String sign;
    /**
     * 类型 10 菜单 20 操作 30 权限等
     */
    private String type;
    /**
     * 对应的url
     */
    private String url;
}
