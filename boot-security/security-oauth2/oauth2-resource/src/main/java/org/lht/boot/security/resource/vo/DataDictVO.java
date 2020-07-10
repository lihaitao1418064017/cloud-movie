package org.lht.boot.security.resource.vo;

import lombok.Data;
import org.lht.boot.security.resource.entity.DataDict;
import org.lht.boot.web.domain.vo.AbstractTreeVO;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/7/10 下午9:25
 **/
@Data
public class DataDictVO extends AbstractTreeVO<DataDict, DataDictVO, String> {

    private String id;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数值 code与parent_id 联合唯一
     */
    private String code;

    /**
     * 参数描述
     */
    private String description;


    private DataDict parent;

}
