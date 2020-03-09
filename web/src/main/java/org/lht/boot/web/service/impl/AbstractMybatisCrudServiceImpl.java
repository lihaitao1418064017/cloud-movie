package org.lht.boot.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.Validate;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.util.ParamMybatisUtil;
import org.lht.boot.web.dao.AbstractMybatisPlusDao;
import org.lht.boot.web.domain.entity.CrudEntity;
import org.lht.boot.web.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description AbstractMybatisCrudServiceImpl: 数据库Service实现类
 * @date 2020/1/8 16:23
 **/

public class AbstractMybatisCrudServiceImpl<E extends CrudEntity<PK>, PK extends Serializable, Dao extends AbstractMybatisPlusDao<E>> implements AbstractCrudService<E, PK> {

    @Autowired
    protected Dao dao;

    @Override
    public int delete(PK pk) {
        return dao.deleteById(pk);
    }

    @Override
    public <Q extends Param> int delete(Q param) {
        return dao.delete(param);
    }

    @Override
    public PK insert(E data) {
        dao.insert(data);
        return data.getId();
    }

    @Override
    public E get(PK id) {
        return dao.selectById(id);
    }

    @Override
    public List<E> get(List<PK> id) {
        return dao.selectBatchIds(id);
    }

    @Override
    public List<E> getAll() {
        return dao.selectList(new QueryWrapper<>());
    }

    @Override
    public long count() {
        return dao.selectCount(new QueryWrapper<>());
    }

    @Override
    public <Q extends Param> PagerResult<E> selectPager(Q param) {
        if (param instanceof QueryParam) {
            QueryParam queryParam = (QueryParam) param;
            return dao.selectPage(queryParam);
        }
        PagerResult<E> page = new PagerResult<>();
        page.setRecords(dao.selectList(ParamMybatisUtil.toQueryWrapper(param)));
        return page;
    }


    @Override
    public <Q extends Param> List<E> select(Q param) {
        return dao.selectList(ParamMybatisUtil.toQueryWrapper(param));
    }

    @Override
    public <Q extends Param> long count(Q param) {
        return dao.selectCount(ParamMybatisUtil.toQueryWrapper(param));
    }

    @Override
    public <Q extends Param> E selectSingle(Q param) {
        QueryWrapper<E> queryWrapper = ParamMybatisUtil.toQueryWrapper(param);
        return dao.selectOne(queryWrapper);
    }

    @Override
    public PK update(PK id, E data) {
        Validate.notNull(data);
        data.setId(id);
        dao.updateById(data);
        return id;
    }

    @Override
    public PK update(E data) {
        Validate.notNull(data.getId());
        dao.updateById(data);
        return data.getId();
    }

    @Override
    public PK upsert(E entity) {
        Validate.notNull(entity);
        E e = dao.selectById(entity.getId());
        if (ObjectUtil.isNotNull(e)) {
            dao.updateById(entity);
        }
        dao.insert(entity);
        return entity.getId();
    }

    @Override
    public List<PK> upsert(Collection<E> entities) {
        entities.forEach(this::upsert);
        return entities.stream().map(E::getId).collect(Collectors.toList());

    }

    @Override
    public PK patch(PK id, E data) {
        data.setId(id);
        dao.updateById(data);
        return id;
    }

    @Override
    public PK patch(E data) {
        Validate.notNull(data.getId());
        dao.updateById(data);
        return data.getId();
    }


}
