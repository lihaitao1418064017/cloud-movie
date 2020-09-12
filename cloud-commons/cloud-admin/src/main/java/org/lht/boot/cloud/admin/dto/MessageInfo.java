package org.lht.boot.cloud.admin.dto;

import lombok.Data;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/23 16:01
 **/
@Data
public class MessageInfo {

    public MessageInfo(String content) {
        this.content = content;
    }

    private String content;
}
