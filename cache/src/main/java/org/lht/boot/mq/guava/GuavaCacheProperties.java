package org.lht.boot.mq.guava;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LiHaitao
 * @description GuavaCacheProperties:
 * @date 2020/1/17 16:23
 **/
@Data
@ConfigurationProperties(prefix = "guava.mq")
public class GuavaCacheProperties {


    /**
     * 默认缓存最大容量
     */
    public final int maxSize = 16;

    /**
     * 默认刷新时间
     */
    public final int refreshTime = 1;

    /**
     * 默认过期时间
     */
    public final int expireTime = 1;


    public String expireMethod;

    public String keyReferenceMethod;

    public String valueReferenceMethod;


}
