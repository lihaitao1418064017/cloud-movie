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

import cn.hutool.core.collection.CollectionUtil;
import org.lht.boot.web.api.param.Param;

import java.util.Collection;

/**
 * 删除Service
 *
 * @param <PK>
 */
public interface DeleteService<PK> extends Service {
    /**
     * 根据主键删除记录
     *
     * @param pk 主键
     * @return 影响记录数
     */
    int delete(PK pk);

    <Q extends Param> int delete(Q param);

    /**
     * 批量删除记录
     *
     * @param ids 主键
     * @return 影响记录数
     */
    default int delete(Collection<PK> ids) {
        if (CollectionUtil.isNotEmpty(ids)) {
            ids.stream().forEach(this::delete);
        } else {
            return 0;
        }
        return ids.size();
    }


}
