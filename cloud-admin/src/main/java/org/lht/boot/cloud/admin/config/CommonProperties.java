package org.lht.boot.cloud.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/23 15:44
 **/
@ConfigurationProperties("cloud.admin.server")
@Data
public class CommonProperties {

    private String appKey = "dingzdaa7bxbbfocvb3q";

    private String appSecret = "N-vssO4AJcuhYi_jkTWIDyl4Ae0wa1b3RYoDR4EJ1oPXwXJcTkEhYqmPYV8SqsY-";

    private String accessToken = "28d23332467733508e6f550e40d43cb8147f2f9b7a613bb7f0f552c3cfb93513";

}
