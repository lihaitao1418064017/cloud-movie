package org.lht.boot.web.service;

import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiHaitao
 * @description 树形结构service，提供关于树型结构的一些查询
 * @date 2020/7/8 17:39
 **/
public interface CrudTreeService<E extends BaseCrudEntity<PK>, PK extends Serializable> extends BaseCrudService<E, PK> {



    List<E> selectAllChildNode(QueryParam queryParam, List<PK> pks);

    List<E> selectChildNode(PK pk);

    List<E> selectAllChildNode(PK pk, QueryParam queryParam);

    List<E> convertToTree(List<E> entities);

    PagerResult<E> selectAllChildNodePager(PK pk, QueryParam queryParam);

    boolean isParentPresent(E e);

    List<E> selectAsTree(PK pk, QueryParam queryParam);

    List<E> selectAsTree(QueryParam queryParam);

}
