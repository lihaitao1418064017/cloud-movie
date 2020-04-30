package org.lht.boot.security.client.entity;

import lombok.Data;

@Data
public class OAuth2Token {

    private String accessToken;

    private String tokenType;

    private String expiresIn;

    private String refreshToken;


}
