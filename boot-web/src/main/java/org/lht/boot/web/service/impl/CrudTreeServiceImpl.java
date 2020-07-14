package org.lht.boot.web.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.api.param.TermEnum;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.lht.boot.web.domain.entity.TreeEntity;
import org.lht.boot.web.service.CrudTreeService;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/9 9:56
 **/
public abstract class CrudTreeServiceImpl<E extends TreeEntity<PK, E>, PK extends Serializable, Dao extends BaseMybatisPlusDao<E>> extends BaseMybatisCrudServiceImpl<E, PK, Dao> implements CrudTreeService<E, PK> {



    @Override
    public List<E> selectAllChildNode(QueryParam queryParam, List<PK> ids) {
        if (ids != null) {
            if (CollectionUtils.isNotEmpty(ids)) {
                List<E> entities = getDao().selectBatchIds(ids);
                entities.forEach((E d) -> {
                    queryParam.or("path", TermEnum.like, d.getPath());
                });
                return select(queryParam);
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public List<E> selectChildNode(PK pk) {
        if (pk != null) {
            E e = getDao().selectById(pk);
            if (e != null) {
                QueryWrapper<E> queryWrapper = new QueryWrapper<E>();
                return getDao().selectList(queryWrapper.like("path", e.getPath()));
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public List<E> selectAllChildNode(PK pk, QueryParam queryParam) {
        if (pk != null) {
            E e = getDao().selectById(pk);
            if (e != null) {
                return select(queryParam.addTerm(Term.build("path", TermEnum.like, e.getPath())));
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public List<E> convertToTree(List<E> entities) {
        if (CollectionUtil.isNotEmpty(entities)) {
            List<E> result = Lists.newArrayList();
            for (E parent : entities) {
                List<E> parentList = entities
                        .stream()
                        .filter(e -> ObjectUtil.isNotNull(parent.getPid()) && parent.getPid().equals(e.getId()))
                        .collect(Collectors.toList());
                if (parent.getPid() == null || CollectionUtil.isEmpty(parentList)) {
                    result.add(parent);
                }
                for (E children : entities) {
                    if (children.getPid() != null && children.getPid().equals(parent.getId())) {
                        if (parent.getChildren() == null) {
                            parent.setChildren(Lists.newArrayList());
                        }
                        parent.getChildren().add(children);
                    }
                }
            }
            return result;
        }
        return Lists.newArrayList();
    }

    @Override
    public PagerResult<E> selectAllChildNodePager(PK pk, QueryParam queryParam) {
        if (pk != null) {
            E e = getDao().selectById(pk);
            if (e != null) {
                return selectPager(queryParam.addTerm(Term.build("path", TermEnum.like, e.getPath())));
            }
        }
        return new PagerResult<>();
    }

    @Override
    public boolean isParentPresent(E e) {
        return getDao().selectById(e.getPid()) != null;
    }

    @Override
    public List<E> selectAsTree(PK pk, QueryParam queryParam) {
        List<E> list = selectAllChildNode(pk, queryParam);
        return convertToTree(list);

    }

    @Override
    public List<E> selectAsTree(QueryParam queryParam) {
        return convertToTree(select(queryParam));
    }

}
