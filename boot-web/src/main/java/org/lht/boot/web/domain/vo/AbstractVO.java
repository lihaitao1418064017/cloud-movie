package org.lht.boot.web.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description AbstractVO:
 * @date 2020/1/15 14:42
 **/
@Getter
@Setter
public abstract class AbstractVO<E, PK extends Serializable> implements CrudVO<E, PK> {


    private Integer status;

    private String creatorCode;

    private Long createTime;

    private String updaterCode;

    private Long updateTime;
}
