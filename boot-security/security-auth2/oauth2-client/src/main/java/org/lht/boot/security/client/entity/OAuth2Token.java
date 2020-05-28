package org.lht.boot.security.client.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class OAuth2Token {

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "token_type")
    private String tokenType;

    @JSONField(name = "expires_in")
    private String expiresIn;

    private String refreshToken;


}
