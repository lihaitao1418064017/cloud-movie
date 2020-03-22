package org.lht.boot.web.domain.vo;

import lombok.Data;
import org.lht.boot.web.domain.entity.User;

/**
 * @author LiHaitao
 * @description UserVO:
 * @date 2020/1/15 14:22
 **/
@Data
public class UserVO implements CrudVO<User, String> {

    private String id;

    private String name;

    private Integer age;
}
