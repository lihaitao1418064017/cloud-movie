package org.lht.boot.mq.redis.lock;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author LiHaitao
 * @description RedisCacheManageProperties:redisLock相关配置
 * @date 2020/1/19 15:35
 **/
@Data
@Configuration
@ConfigurationProperties(value = "redis.lock.cluster")
public class RedisClusterLockProperties {

    private List<String> addressList = Lists.newArrayList("redis://localhost:6379");

    /**
     * （集群扫描间隔时间）
     * 默认值1000
     * 对Redis集群节点状态扫描的时间间隔 单位ms
     */
    private Integer scanInterval = 1000;

    /**
     * 分片数量
     * 默认值： 231 用于指定数据分片过程中的分片数量。支持数据分片/框架结构有：集（Set）、映射（Map）
     * 、BitSet、Bloom filter, Spring Cache和Hibernate Cache等.
     */
    private Integer slots = 231;

    /**
     * 从节点发布和订阅连接的最小空闲连接数
     */
    private Integer subscriptionConnectionMinimumIdleSize = 1;

    /**
     * 从节点发布和订阅连接池大小
     * 默认值：50
     * 多从节点的环境里，每个 从服务节点里用于发布和订阅连接的连接池最大容量。连接池的连接数量自动弹性伸缩。
     */
    private Integer subscriptionConnectionPoolSize = 50;

    /**
     * 从节点连接池大小
     */
    private Integer slaveConnectionMinimumIdleSize = 64;

    /**
     * 主节点最小空闲连接数
     * 默认值：32
     * 多节点的环境里，每个 主节点的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。
     */
    private Integer masterConnectionMinimumIdleSize = 32;

    /**
     * 默认值：64
     * <p>
     * 多主节点的环境里，每个 主节点的连接池最大容量。连接池的连接数量自动弹性伸缩。
     */
    private Integer masterConnectionPoolSize = 64;

    /**
     * idleConnectionTimeout（连接空闲超时，单位：毫秒）
     * 默认值：10000
     * <p>
     * 如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，
     * 那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。
     */
    private Integer idleConnectionTimeout = 10000;

    /**
     * connectTimeout（连接超时，单位：毫秒）
     * 默认值：10000
     * 同任何节点建立连接时的等待超时。时间单位是毫秒。
     */
    private Integer connectTimeout = 10000;

    /**
     * timeout（命令等待超时，单位：毫秒）
     * 默认值：3000
     * <p>
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时。
     */
    private Integer timeout = 3000;

    /**
     * retryAttempts（命令失败重试次数）
     * 默认值：3
     * <p>
     * 如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。
     * 如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。
     */
    private Integer retryAttempts = 3;

    /**
     * retryInterval（命令重试发送时间间隔，单位：毫秒）
     * 默认值：1500
     * <p>
     * 在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。
     */

    private Integer retryInterval = 1500;

    /**
     * reconnectionTimeout（重新连接时间间隔，单位：毫秒）
     * 默认值：3000
     * <p>
     * 当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。
     */
    private Integer reconnectionTimeout = 3000;


    /**
     * password（密码）
     * 默认值：null
     * <p>
     * 用于节点身份验证的密码。
     */

    private String password;


    /**
     * subscriptionsPerConnection（单个连接最大订阅数量）
     * 默认值：5
     * <p>
     * 每个连接的最大订阅数量。
     */
    private Integer subscriptionsPerConnection = 5;


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
    private Boolean sslEnableEndpointIdentification = true;

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


    private Boolean checkSlotsCoverage = false;


    public Boolean keepAlive = false;

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
     * （Netty线程池数量）
     * 默认值: 当前处理核数量 * 2
     */
    private Integer nettyThreads = 8;
}
