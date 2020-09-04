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

    /**
     * 根据ids查询所有子节点
     *
     * @param queryParam
     * @param pks        指定id
     * @return 列表结果
     */
    List<E> selectAllChildNode(QueryParam queryParam, List<PK> pks);

    /**
     * 查询执行id下的子节点
     *
     * @param pk 指定id
     * @return 子节点列表
     */
    List<E> selectChildNode(PK pk);

    /**
     * 根据id和参数获取所有子节点
     *
     * @param pk         指定id
     * @param queryParam
     * @return 子节点列表
     */
    List<E> selectAllChildNode(PK pk, QueryParam queryParam);

    /**
     * 转换树形结果
     *
     * @param entities 要转换的列表
     * @return 转换后的树形结果
     */
    List<E> convertToTree(List<E> entities);

    /**
     * 查询左右子节点的分页结果
     *
     * @param pk         指定的id
     * @param queryParam
     * @return 分页结果
     */
    PagerResult<E> selectAllChildNodePager(PK pk, QueryParam queryParam);

    /**
     * 判断是否存在父级
     *
     * @param e 指定的节点
     * @return 是否存在
     */
    boolean isParentPresent(E e);

    /**
     * 条件查询，结果为树形结果
     *
     * @param pk         指定查询的id
     * @param queryParam
     * @return 树形结果
     */
    List<E> selectAsTree(PK pk, QueryParam queryParam);

    /**
     * 条件查询，结果为树形结果
     *
     * @param queryParam
     * @return 树形结果
     */
    List<E> selectAsTree(QueryParam queryParam);

}
