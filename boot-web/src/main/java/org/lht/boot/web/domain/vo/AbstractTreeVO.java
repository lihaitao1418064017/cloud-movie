package org.lht.boot.web.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiHaitao
 * @description AbstractTreeVO:
 * @date 2020/7/10 19:42
 **/
@Getter
@Setter
public abstract class AbstractTreeVO<E, VO extends AbstractTreeVO, PK extends Serializable> extends AbstractVO<E, PK> {

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
     * 下级
     */
    private List<VO> children;

}
