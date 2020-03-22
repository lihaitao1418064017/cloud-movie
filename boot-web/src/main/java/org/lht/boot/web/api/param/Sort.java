package org.lht.boot.web.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiHaitao
 * @description Sort: 排序
 * @date 2020/1/14 9:36
 **/
@Data
@NoArgsConstructor
public class Sort {

    private String name;

    private SortEnum order = SortEnum.DESC;

    public Sort asc(String name) {
        this.name = name;
        this.order = SortEnum.ASC;
        return this;
    }

    public Sort desc(String name) {
        this.name = name;
        this.order = SortEnum.DESC;
        return this;
    }
}
