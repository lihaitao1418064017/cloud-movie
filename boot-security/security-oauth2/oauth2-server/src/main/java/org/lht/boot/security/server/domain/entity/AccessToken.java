package org.lht.boot.security.server.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description AccessToken:服务端返回的accessToken结构
 * @date 2020/5/28 15:33
 **/
@Data
@Builder
public class AccessToken implements Serializable {

    /**
     * 令牌
     */
    private String accessToken;

    /**
     * 令牌类型
     */
    private String tokenType;

    /**
     * 令牌过期时间
     */
    private String expiresIn;

    /**
     * 刷新令牌
     */
    private String refreshToken;


}
