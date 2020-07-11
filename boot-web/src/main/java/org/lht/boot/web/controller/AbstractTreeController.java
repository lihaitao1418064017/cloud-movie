package org.lht.boot.web.controller;

import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.R;
import org.lht.boot.web.api.param.util.ParamServletUtil;
import org.lht.boot.web.domain.entity.TreeEntity;
import org.lht.boot.web.domain.vo.AbstractTreeVO;
import org.lht.boot.web.service.CrudTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public R<List<E>> selectAllChildNode(QueryParam queryParam, List<PK> ids, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAllChildNode(queryParam, ids));
    }

    @GetMapping("/{id}")
    public R<List<E>> selectChildNode(@PathVariable PK pk) {
        return R.ok(service.selectChildNode(pk));
    }

    @GetMapping("/param/{id}")
    public R<List<E>> selectAllChildNode(@PathVariable PK pk, QueryParam queryParam, HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAllChildNode(pk, queryParam));
    }

    @GetMapping("/page/{id}")
    public R<PagerResult<E>> selectAllChildNodePager(@PathVariable PK pk, QueryParam queryParam,HttpServletRequest request, HttpServletResponse response) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAllChildNodePager(pk,queryParam));
    }
    @GetMapping("/isParentPresent")
    public R<Boolean> isParentPresent(E e){
        return R.ok(service.isParentPresent(e));
    }

    @GetMapping("/tree/{id}")
    public R<List<E>> selectAsTree(PK pk, QueryParam queryParam,HttpServletRequest request, HttpServletResponse response){
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAsTree(pk,queryParam));
    }

    @GetMapping("/param/tree")
    public R<List<E>> selectAsTree(QueryParam queryParam,HttpServletRequest request, HttpServletResponse response){
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.selectAsTree(queryParam ));
    }

}
