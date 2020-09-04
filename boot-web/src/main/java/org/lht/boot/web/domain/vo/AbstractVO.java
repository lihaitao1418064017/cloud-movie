package org.lht.boot.web.domain.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LiHaitao
 * @description AbstractVO:
 * @date 2020/1/15 14:42
 **/
@Getter
@Setter
public abstract class AbstractVO<E, PK> implements CrudVO<E> {

    /**
     * 主键id
     */
    private PK id;

    /**
     * 状态码，0无效，1有效
     */
    private Integer status;
    /**
     * 创建用户
     */
    private String creatorUser;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Long updateTime;
}
