package org.lht.boot.security.server.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description AccessToken:
 * @date 2020/5/28 15:33
 **/
@Data
@Builder
public class AccessToken implements Serializable {


    private String accessToken;

    private String tokenType;

    private String expiresIn;

    private String refreshToken;

}
