package org.lht.boot.web.domain.entity;

import io.searchbox.annotations.JestId;
import lombok.Data;

/**
 * @author LiHaitao
 * @description User:
 * @date 2020/1/2 17:08
 **/
@Data
@EsEntity(index = "teacher", type = "teacher", alias = "teacher")
public class Teacher extends BaseCrudEntity<String> {

    @JestId
    private String id;

    private String name;

    private Integer age;

    private Integer sex;

    private String identify;

    private String grade;

    private User user;

}
