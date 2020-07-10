package org.lht.boot.web.domain.entity;

import lombok.Data;

import java.util.List;


/**
 * @author LiHaitao
 * @description 树形结构实体类
 * @date 2020/7/8 15:56
 **/
@Data
public abstract class TreeEntity<PK, E> extends BaseCrudEntity<PK> {

    private PK pid;

    private String path;

    private Integer level;

    private List<E> children;


}
