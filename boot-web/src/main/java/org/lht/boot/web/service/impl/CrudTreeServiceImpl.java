package org.lht.boot.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.api.param.TermEnum;
import org.lht.boot.web.dao.CrudTreeDao;
import org.lht.boot.web.domain.entity.TreeEntity;
import org.lht.boot.web.service.CrudTreeService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/9 9:56
 **/
public abstract class CrudTreeServiceImpl<E extends TreeEntity<PK, E>, PK extends Serializable, Dao extends CrudTreeDao<E>> extends BaseMybatisCrudServiceImpl<E, PK, Dao> implements CrudTreeService<E, PK> {


    @Override
    public List<E> selectAllChildNode(QueryParam queryParam, PK... pks) {
        if (pks != null) {
            List<PK> ids = Arrays.asList(pks);
            if (CollectionUtils.isNotEmpty(ids)) {
                List<E> entities = dao.selectBatchIds(ids);
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
            E e = dao.selectById(pk);
            if (e != null) {
                QueryWrapper<E> queryWrapper = new QueryWrapper<E>();
                return dao.selectList(queryWrapper.like("path", e.getPath()));
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public List<E> selectAllChildNode(PK pk, QueryParam queryParam) {
        if (pk != null) {
            E e = dao.selectById(pk);
            if (e != null) {
                return select(queryParam.addTerm(Term.build("path", TermEnum.like, e.getPath())));
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public List<E> convertToTree(List<E> entities) {


        return null;
    }

    @Override
    public PagerResult<E> selectAllChildNodePager(PK pk, QueryParam queryParam) {
        if (pk != null) {
            E e = dao.selectById(pk);
            if (e != null) {
                return selectPager(queryParam.addTerm(Term.build("path", TermEnum.like, e.getPath())));
            }
        }
        return new PagerResult<>();
    }

    @Override
    public boolean isParentPresent(E e) {
        return dao.selectById(e.getPid()) != null;
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

    @Override
    public E traceToTop(PK pk, QueryParam queryParam) {
        return null;
    }
}
