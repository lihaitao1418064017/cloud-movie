package org.lht.boot.web.controller;

import org.lht.boot.web.api.param.R;
import org.lht.boot.web.service.InsertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.lht.boot.web.api.param.R.ok;

/**
 * @author LiHaitao
 * @description InsertController:
 * @date 2020/1/15 13:47
 **/
public interface InsertController<E, PK, VO> {


    <S extends InsertService<E, PK>> S getService();

    /**
     * 添加
     *
     * @param data
     * @return
     */
    @PostMapping
    default R<PK> add(@RequestBody VO data) {
        return ok(getService().insert(voToEntity(data)));
    }

    /**
     * VO to Entity
     *
     * @param model
     * @return
     */
    E voToEntity(VO model);
}
