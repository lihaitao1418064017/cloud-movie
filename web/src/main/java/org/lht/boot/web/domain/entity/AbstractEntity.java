package org.lht.boot.web.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description AbstractEntity:
 * @date 2020/1/2 17:12
 **/
@Getter
@Setter
public abstract class AbstractEntity<PK extends Serializable> implements CrudEntity<PK> {


    private Integer status;

    private String creatorCode;

    private Long createTime;

    private String updaterCode;

    private Long updateTime;


}
