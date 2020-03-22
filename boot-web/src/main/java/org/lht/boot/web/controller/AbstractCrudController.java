package org.lht.boot.web.controller;

import cn.hutool.core.bean.BeanUtil;
import org.lht.boot.lang.util.ClassUtil;
import org.lht.boot.lang.util.ReflectionUtil;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.domain.vo.CrudVO;
import org.lht.boot.web.service.CrudService;

/**
 * @author LiHaitao
 * @description AbstractCrudController:
 * @date 2020/1/15 14:11
 **/
public abstract class AbstractCrudController<E, PK, VO extends CrudVO<E, PK>, Q extends Param> implements CrudController<E, PK, VO, Q> {

    protected final Class<VO> voClass;
    protected final Class<E> eClass;

    @Override
    public abstract CrudService<E, PK> getService();

    /**
     * 获取VO Class对象
     */
    public AbstractCrudController() {
        this.voClass = (Class<VO>) ClassUtil.getGenericType(this.getClass(), 2);
        this.eClass = (Class<E>) ClassUtil.getGenericType(this.getClass(), 0);
    }

    @Override
    public E voToEntity(VO model) {
        E e = ReflectionUtil.newInstance(eClass);
        BeanUtil.copyProperties(model, e);
        return e;
    }

    @Override
    public VO entityToVo(E entity) {
        VO vo = ReflectionUtil.newInstance(voClass);
        return vo.covertToVO(entity);
    }

}
