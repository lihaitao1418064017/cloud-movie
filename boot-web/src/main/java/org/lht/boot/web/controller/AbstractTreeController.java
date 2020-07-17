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
 * @description AbstractTreeController
 * @date 2020/7/10 17:18
 **/
public abstract class AbstractTreeController<E extends TreeEntity<PK, E>, PK extends Serializable, VO extends AbstractTreeVO<E, VO, PK>, S extends CrudTreeService<E, PK>> extends AbstractCrudController<E, PK, VO, QueryParam> {

    @Autowired
    protected S service;

    @Override
    public S getService() {
        return service;
    }

    @GetMapping("/allChildNode")
    @AccessLogger("批量根据id查询所有子节点数据")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectAllChildNode(QueryParam queryParam, List<PK> ids, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAllChildNode(queryParam, ids));
    }

    @GetMapping("/{id}")
    @AccessLogger("根据id查询子节点数据")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectChildNode(@PathVariable PK pk) {
        return R.ok(service.selectChildNode(pk));
    }

    @GetMapping("/param/{id}")
    @AccessLogger("根据条件查询")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectAllChildNode(@PathVariable PK pk, QueryParam queryParam, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAllChildNode(pk, queryParam));
    }

    @GetMapping("/page/{id}")
    @AccessLogger("分页查询")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<PagerResult<E>> selectAllChildNodePager(@PathVariable PK pk, QueryParam queryParam, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAllChildNodePager(pk, queryParam));
    }

    @GetMapping("/isParentPresent")
    @AccessLogger("判断腹肌是否存在")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<Boolean> isParentPresent(E e) {
        return R.ok(service.isParentPresent(e));
    }

    @GetMapping("/tree/{id}")
    @AccessLogger("根据id获取树形结果")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectAsTree(PK pk, QueryParam queryParam, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAsTree(pk, queryParam));
    }

    @GetMapping("/param/tree")
    @AccessLogger("根据条件获取树形结果")
    @PreAuthorize("hasPermission('*','QUERY')")
    public R<List<E>> selectAsTree(QueryParam queryParam, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAsTree(queryParam));
    }

}
