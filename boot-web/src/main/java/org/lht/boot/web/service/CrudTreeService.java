package org.lht.boot.web.service;

import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.domain.entity.BaseCrudEntity;
import org.lht.boot.web.domain.entity.TreeEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/8 17:39
 **/
public interface CrudTreeService<E extends TreeEntity, PK extends Serializable> extends BaseCrudService<BaseCrudEntity<PK>, PK> {


    List<E> selectAllChildNode(QueryParam var1, PK... var2);

    List<E> selectChildNode(PK var1);

    List<E> selectAllChildNode(PK var1, QueryParam var2);

    List<E> convertToTree(List<E> var1);

    PagerResult<E> selectAllChildNodePager(PK var1, QueryParam var2);

    boolean isParentPresent(E var1);

    List<E> selectAsTree(PK var1, QueryParam var2);

    List<E> selectAsTree(QueryParam var1);

    E traceToTop(PK var1, QueryParam var2);
}
