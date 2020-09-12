package org.lht.boot.web.api.param;

import lombok.Builder;
import lombok.Getter;

/**
 * @Description: 聚合组装器
 * @Author: Lihaitao
 * @Date: 2020/3/9 14:47
 * @UpdateUser:
 * @UpdateRemark:
 */
@Getter
@Builder
public class Aggregation {

    private String field;
    private AggregationEnum type;
    private String as;

}
