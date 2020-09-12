package org.lht.boot.web.domain.entity;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description EsEntity:
 * @date 2020/1/2 16:25
 **/
public interface Entity<PK> extends Serializable {

    String ID = "id";

    PK getId();

    void setId(PK id);
}
