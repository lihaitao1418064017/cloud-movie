package org.lht.boot.security.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/5/28 17:18
 */
@Data
public class AuthPermission implements Serializable {

    /**
     * 主键id ，自增长
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;

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


    /**
     * 树结构编码,用于快速查找, 每一层由4位字符组成,用-分割
     * 如第一层:0001 第二层:0001-0001 第三层:0001-0001-0001
     */
    private String path;

    /**
     * 排序索引
     */
    private Long sortIndex;

    private Integer level;

    private String pid;

    private List<AuthPermission> children;


}
