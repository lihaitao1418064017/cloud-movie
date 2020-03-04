package org.lht.boot.mq.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description User:
 * @date 2020/1/16 14:49
 **/
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -22342342342666L;


    private String name;

    private Integer age;
}
