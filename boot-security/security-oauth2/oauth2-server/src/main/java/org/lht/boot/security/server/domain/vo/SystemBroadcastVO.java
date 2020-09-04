package org.lht.boot.security.server.domain.vo;

import lombok.Data;
import org.lht.boot.security.server.domain.entity.SystemBroadcast;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * @author LiHaitao
 * @description 系统广播
 * @date 2020/7/14 17:31
 **/
@Data
public class SystemBroadcastVO extends AbstractVO<SystemBroadcast, String> {


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
