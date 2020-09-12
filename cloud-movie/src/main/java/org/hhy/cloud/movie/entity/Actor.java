package org.hhy.cloud.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * Description: 演员
 *
 * @Author lht
 * @Date 2020/9/12 下午9:53
 **/
@Data
public class Actor extends BaseCrudEntity<String> {

    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 地区
     */
    private String area;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 出生年月
     */
    private String birthday;
    /**
     * 职业
     */
    private String vocational;





}
