package org.lht.boot.security.config.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/22 4:08 PM
 **/
@ConfigurationProperties(
        prefix = "spring.security.oauth2.check"
)
@Data
public class AuthorizationServerProperties {

    private String checkTokenAccess;


}
