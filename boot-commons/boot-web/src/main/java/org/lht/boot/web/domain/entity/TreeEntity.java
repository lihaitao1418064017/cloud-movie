package org.lht.boot.web.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;


/**
 * @author LiHaitao
 * @description 树形结构实体类
 * @date 2020/7/8 15:56
 **/
@Data
public abstract class TreeEntity<PK, E> extends BaseCrudEntity<PK> {

    /**
     * 父id
     */
    private PK pid;

    /**
     * path
     */
    private String path;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 数据库不存在字段
     */
    @TableField(exist = false)
    private List<E> children;


}
