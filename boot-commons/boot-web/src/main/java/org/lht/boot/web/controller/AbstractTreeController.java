package org.lht.boot.web.controller;

import org.lht.boot.lang.util.R;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.util.ParamServletUtil;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.domain.entity.TreeEntity;
import org.lht.boot.web.domain.vo.AbstractTreeVO;
import org.lht.boot.web.service.CrudTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * @author LiHaitao
 * @description AbstractTreeController  树形controller
 * @date 2020/7/10 17:18
 **/
public abstract class AbstractTreeController<E extends TreeEntity<PK, E>, PK extends Serializable, VO extends AbstractTreeVO<E, VO, PK>, S extends CrudTreeService<E, PK>> extends AbstractCrudController<E, PK, VO, QueryParam> {

    @Autowired
    protected S service;

    @Override
    public S getService() {
        return service;
    }

    /**
     * 批量根据id查询所有子节点数据
     *
     * @param queryParam 查询条件
     * @param ids        查询id参数
     * @param request
     * @param response
     * @return 所有子节点
     */
    @GetMapping("/allChildNode")
    @AccessLogger("批量根据id查询所有子节点数据")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectAllChildNode(QueryParam queryParam, List<PK> ids, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAllChildNode(queryParam, ids));
    }

    /**
     * 根据id查询子节点数据
     *
     * @param pk 主键id
     * @return 子节点列表
     */
    @GetMapping("/{id}")
    @AccessLogger("根据id查询子节点数据")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectChildNode(@PathVariable PK pk) {
        return R.ok(service.selectChildNode(pk));
    }

    /**
     * 根据条件查询
     *
     * @param pk         主键id
     * @param queryParam 查询条件
     * @param request
     * @param response
     * @return 列表
     */
    @GetMapping("/param/{id}")
    @AccessLogger("根据条件查询")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectAllChildNode(@PathVariable PK pk, QueryParam queryParam, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAllChildNode(pk, queryParam));
    }

    /**
     * 分页查询
     *
     * @param pk         父节点主键
     * @param queryParam 查询参数
     * @param request
     * @param response
     * @return 分页结果
     */
    @GetMapping("/page/{id}")
    @AccessLogger("分页查询")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<PagerResult<E>> selectAllChildNodePager(@PathVariable PK pk, QueryParam queryParam, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAllChildNodePager(pk, queryParam));
    }

    /**
     * 判断父级是否存在
     *
     * @param e 指定节点
     * @return 是否存在
     */
    @GetMapping("/isParentPresent")
    @AccessLogger("判断父级是否存在")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<Boolean> isParentPresent(E e) {
        return R.ok(service.isParentPresent(e));
    }

    /**
     * 根据id获取树形结果
     *
     * @param pk         指定id的树形结果
     * @param queryParam
     * @param request
     * @param response
     * @return 列表
     */
    @GetMapping("/tree/{id}")
    @AccessLogger("根据id获取树形结果")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectAsTree(PK pk, QueryParam queryParam, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAsTree(pk, queryParam));
    }

    /**
     * 根据查询条件获取树形结果
     *
     * @param queryParam
     * @param request
     * @param response
     * @return 列表
     */
    @GetMapping("/param/tree")
    @AccessLogger("根据条件获取树形结果")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectAsTree(QueryParam queryParam, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAsTree(queryParam));
    }

}
