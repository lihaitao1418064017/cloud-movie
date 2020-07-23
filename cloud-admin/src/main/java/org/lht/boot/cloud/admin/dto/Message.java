package org.lht.boot.cloud.admin.dto;

import lombok.Data;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/23 16:00
 **/
@Data
public class Message {

    private String msgtype;

    private MessageInfo text;
}
