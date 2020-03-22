package org.lht.boot.cache.redis.lock;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description RedisCacheManageProperties:redisLock相关配置
 * @date 2020/1/19 15:35
 **/
@Data
@Configuration
@ConfigurationProperties(value = "redis.lock.single")
public class RedisLockProperties {


    private String serverAddress = "redis://localhost:6379";

    private Integer connectionPoolSize = 10;

    private Integer subscriptionConnectionMinimumIdleSize = 1;

    private Integer connectionMinimumIdleSize = 10;

    private Long dnsMonitoringInterval = 30000L;

    private Integer subscriptionConnectionPoolSize = 50;

    private Integer timeout = 100000;

    private String password;

    private Integer retryAttempts = 3;

    private Integer retryInterval = 3000;


    public Boolean keepAlive = false;

    public int connectTimeout = 100000;
    /**
     * 处理器核数*2
     * 默认值: 当前处理核数量 * 2
     */
    private Integer threads = 8;

    /**
     * （Netty线程池数量）
     * 默认值: 当前处理核数量 * 2
     */
    private Integer nettyThreads = 8;

    //    /**
    //     * 线程池
    //     */
    //    private Integer executors;
    //    /**
    //     * 用于特别指定一个EventLoopGroup. EventLoopGroup是用来处理所有通过Netty
    //     * 与Redis服务之间的连接发送和接受的消息。每一个Redisson都会在默认情况下自己创建管理
    //     * 一个EventLoopGroup实例。因此，如果在同一个JVM里面可能存在多个Redisson实例的情况下，
    //     * 采取这个配置实现多个Redisson实例共享一个EventLoopGroup的目的
    //     */
    //    private Integer eventLoopGroup;
    //
    //    private Integer transportMode;
    /**
     * 监控锁的看门狗超时，单位：毫秒 默认值：30000
     */
    private Integer lockWatchdogTimeout = 30000;

    /**
     * keepPubSubOrder
     * 默认值true
     */
    private Boolean keepPubSubOrder = true;

    /**
     * 可选模式：
     * HIGHER_THROUGHPUT - 将高性能引擎切换到 高通量 模式。
     * LOWER_LATENCY_AUTO - 将高性能引擎切换到 低延时 模式并自动探测最佳设定。
     * LOWER_LATENCY_MODE_1 - 将高性能引擎切换到 低延时 模式并调整到预设模式
     * 1。 LOWER_LATENCY_MODE_2 - 将高性能引擎切换到 低延时 模式并调整到预设模式
     * 2。 NORMAL - 将高性能引擎切换到 普通 模式
     */
    //    private String performanceMode = "HIGHER_THROUGHPUT";


    /**
     * clientName（客户端名称）
     * 默认值：null
     * <p>
     * 在Redis节点里显示的客户端名称。
     */
    private String clientName;

    /**
     * sslEnableEndpointIdentification（启用SSL终端识别）
     * 默认值：true
     * <p>
     * 开启SSL终端识别能力
     */
    private Boolean sslEnableEndpointIdentification = false;

    /**
     * sslProvider（SSL实现方式）
     * 默认值：JDK
     * <p>
     * 确定采用哪种方式（JDK或OPENSSL）来实现SSL连接。
     */
    private String sslProvider = "JDK";

    /**
     * sslTruststore（SSL信任证书库路径）
     * 默认值：null
     * <p>
     * 指定SSL信任证书库的路径。
     */
    private String sslTruststore;

    /**
     * sslTruststorePassword（SSL信任证书库密码）
     * 默认值：null
     * <p>
     * 指定SSL信任证书库的密码。
     */
    private String sslTruststorePassword;

    /**
     * sslKeystore（SSL钥匙库路径）
     * 默认值：null
     * <p>
     * 指定SSL钥匙库的路径。
     */
    private String sslKeystore;

    /**
     * sslKeystorePassword（SSL钥匙库密码）
     * 默认值：null
     * <p>
     * 指定SSL钥匙库的密码。
     */
    private String sslKeystorePassword;
}
