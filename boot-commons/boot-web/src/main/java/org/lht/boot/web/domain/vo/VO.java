package org.lht.boot.web.domain.vo;

import org.lht.boot.web.domain.mapper.CovertToVO;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description VO:
 * @date 2020/1/15 14:40
 **/
public interface VO<E> extends Serializable, CovertToVO<E> {

}
