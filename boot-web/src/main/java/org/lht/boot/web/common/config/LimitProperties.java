package org.lht.boot.web.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 限流配置
 *
 * @Author lht
 * @Date 2020/4/1 下午9:33
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "api.limit")
public class LimitProperties {

    /**
     * 秒
     */
    private Integer period=3;

    /**
     * 次数
     */
    private Integer count=1;
}
