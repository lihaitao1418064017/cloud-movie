package org.lht.boot.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.Validate;
import org.lht.boot.web.api.param.*;
import org.lht.boot.web.api.param.util.ParamMybatisUtil;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.lht.boot.web.domain.entity.BaseCrudEntity;
import org.lht.boot.web.service.BaseCrudService;
import org.lht.boot.web.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description BaseMybatisCrudServiceImpl: 数据库Service实现类
 * @date 2020/1/8 16:23
 **/

public class BaseMybatisCrudServiceImpl<E extends BaseCrudEntity<PK>, PK extends Serializable, Dao extends BaseMybatisPlusDao<E>> implements BaseCrudService<E, PK> {




    @Autowired
    protected Dao dao;

    public Dao getDao() {
        return this.dao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(PK pk) {
        return dao.deleteById(pk);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <Q extends Param> int delete(Q param) {
        return dao.delete(param);
    }

    @Override
    @Transactional
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
    @Transactional(rollbackFor = Exception.class)
    public PK update(PK id, E data) {
        Validate.notNull(data);
        data.setId(id);
        dao.updateById(data);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PK update(E data) {
        Validate.notNull(data.getId());
        dao.updateById(data);
        return data.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public List<PK> upsert(Collection<E> entities) {
        entities.forEach(this::upsert);
        return entities.stream().map(E::getId).collect(Collectors.toList());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PK patch(PK id, E data) {
        data.setId(id);
        dao.updateById(data);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PK patch(E data) {
        Validate.notNull(data.getId());
        dao.updateById(data);
        return data.getId();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int patch(UpdateParam<E> param) {
        return dao.update((E) param.getData(), ParamMybatisUtil.toQueryWrapper(param));
    }

    @Override
    public boolean editCheckUnique(PK pk, String name, Object value) {
        E e = this.selectSingle(QueryParam.build(Term.build(name, value)));
        if (e==null||e.getId().equals(pk)){
            return false;
        }
        return true;
    }


}
