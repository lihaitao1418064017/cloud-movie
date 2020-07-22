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

    private PK pid;

    private String path;

    private Integer level;

    private List<VO> children;

}
