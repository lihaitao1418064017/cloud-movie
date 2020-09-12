package org.lht.boot.web.domain.mapper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;

/**
 * @author LiHaitao
 * @description CovertToVO: entity转换vo
 * @date 2020/1/15 14:51
 **/
public interface CovertToVO<E> {


    default <VO extends CovertToVO<E>> VO covertToVO(E source) {
        if (ObjectUtil.isNull(source)) {
            return null;
        }
        BeanUtil.copyProperties(source, this);
        return (VO) this;
    }


}
