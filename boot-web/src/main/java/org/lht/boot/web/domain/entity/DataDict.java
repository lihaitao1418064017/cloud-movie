package org.lht.boot.web.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiHaitao
 * @description 数据字典
 * @date 2020/7/10 14:23
 **/
@Data
@TableName("data_dict")
public class DataDict extends TreeEntity<String, DataDict> {

    @TableId(type = IdType.UUID)
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
