package org.hhy.cloud.movie.vo;

import lombok.Data;
import org.hhy.cloud.movie.entity.Actor;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * Description: 演员
 *
 * @Author lht
 * @Date 2020/9/12 下午10:29
 **/
@Data
public class ActorVO extends AbstractVO<Actor,String> {

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
