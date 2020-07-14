package org.lht.boot.security.server.domain.entity;

import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;
import org.lht.boot.web.domain.entity.EsEntity;

/**
 * @author LiHaitao
 * @description 广播墙
 * @date 2020/7/13 14:23
 **/
@Data
@EsEntity(index = "system_broadcast", type = "system_broadcast", alias = "system_broadcast")
public class SystemBroadcast extends BaseCrudEntity<String> {

    private String id;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 发布时间
     */
    private Long noticeTime;


}
