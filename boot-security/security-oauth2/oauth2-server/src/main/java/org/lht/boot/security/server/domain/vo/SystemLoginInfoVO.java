package org.lht.boot.security.server.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.web.domain.vo.AbstractVO;

import java.util.Date;

/**
 * @author LiHaitao
 * @description 系统访问记录
 * @date 2020/7/14 17:31
 **/
@Data
public class SystemLoginInfoVO extends AbstractVO<SystemLoginInfo, String> {


    private String id;
    /**
     * 用户账号
     */
    private String username;


    /**
     * 地址
     */
    private String ipAddress;

    /**
     * 登录地址
     */
    private String address;

    /**
     * 描述
     */
    private String msg;

    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date accessTime;


}
