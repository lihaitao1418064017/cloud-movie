/*
 *
 *  * Copyright 2016 http://www.kedacomframework.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.lht.boot.web.service;

import org.apache.commons.lang3.Validate;

import java.util.Collection;
import java.util.List;

/**
 * 更新service
 *
 * @param <E>
 * @param <PK>
 */
public interface UpdateService<E, PK> extends Service {
    /**
     * 修改记录信息
     *
     * @param data 要修改的对象
     * @return 影响记录数
     */
    PK update(PK id, E data);

    /**
     * 修改记录信息
     *
     * @param data
     * @return
     */
    PK update(E data);

    /**
     * 批量修改记录
     *
     * @param entities 要修改的记录集合
     * @return 影响记录数
     */
    default int update(Collection<E> entities) {
        Validate.notEmpty(entities);
        entities.forEach(this::update);
        return entities.size();
    }

    /**
     * 保存或修改
     *
     * @param entity 要修改的数据
     * @return 主键
     */
    PK upsert(E entity);


    /**
     * 保存或修改
     *
     * @param entities 要修改的数据
     * @return 主键列表
     */
    List<PK> upsert(Collection<E> entities);

    /**
     * 根据id 部分修改
     *
     * @param id
     * @param data 修改的数据
     * @return
     */
    PK patch(PK id, E data);

    /**
     * 根据id修改
     *
     * @param data
     * @return
     */
    PK patch(E data);

    /**
     * 批量部分修改
     *
     * @param entities 修改的数据集合
     * @return
     */
    default int patch(Collection<E> entities) {
        Validate.notEmpty(entities);
        entities.forEach(this::patch);
        return entities.size();
    }

    ;
}
