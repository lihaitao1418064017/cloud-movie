package org.lht.boot.web.controller;

import org.apache.commons.lang3.Validate;
import org.lht.boot.web.api.param.R;
import org.lht.boot.web.service.UpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description UpdateController:
 * @date 2020/1/14 20:11
 **/
public interface UpdateController<E, PK, VO> {


    <S extends UpdateService<E, PK>> S getService();

    /**
     * 更新
     *
     * @param vo
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    default R update(@RequestBody VO vo) {
        PK pk = getService().update(voToEntity(vo));
        return R.ok(pk);
    }


    /**
     * 根据id部分更新
     *
     * @param id
     * @param data
     * @return
     */
    @PatchMapping(path = "/{id}")
    default R<PK> patchByPrimaryKey(@PathVariable PK id, @RequestBody(required = false) VO data) {
        Validate.notNull(data, "patch data cannot be null.");
        return R.ok(getService().patch(id, voToEntity(data)));
    }

    /**
     * 保存或更新
     *
     * @param data
     * @return
     */
    @PutMapping
    default R<PK> saveOrUpdate(@RequestBody VO data) {
        return R.ok(getService().upsert(voToEntity(data)));
    }

    /**
     * vo to entity
     *
     * @param vo
     * @return E
     */
    E voToEntity(VO vo);

    /**
     * vos to entities
     *
     * @param vos
     * @return List<E>
     */
    default List<E> vosToEntities(Collection<VO> vos) {
        return vos.stream().map(this::voToEntity).collect(Collectors.toList());
    }

}
