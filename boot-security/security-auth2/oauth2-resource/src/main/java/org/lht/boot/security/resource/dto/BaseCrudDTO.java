package org.lht.boot.security.resource.dto;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description BaseCrudDTO:
 * @date 2020/6/23 16:11
 **/
public class BaseCrudDTO implements Serializable {

    private Integer status;

    private String creatorId;

    private Long createTime;

    private String updateId;

    private Long updateTime;
}
