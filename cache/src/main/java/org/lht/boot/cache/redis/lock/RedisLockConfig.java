package org.lht.boot.cache.redis.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author LiHaitao
 * @description RedisLockConfig:redis 锁配置
 * @date 2020/1/19 14:57
 **/
@Configuration
public class RedisLockConfig {


    @Autowired
    private RedisLockProperties redisLockProperties;

    @Autowired
    private RedisClusterLockProperties redisClusterLockProperties;

    /**
     * 单节点
     *
     * @return
     */
    @Bean("single")
    @ConditionalOnProperty(prefix = "redis.single", name = "enable", havingValue = "true", matchIfMissing = true)
    public RLock rLock() {
        Config config = new Config();
        config.setThreads(redisLockProperties.getThreads())
                .setNettyThreads(redisLockProperties.getNettyThreads())
                .setLockWatchdogTimeout(redisLockProperties.getLockWatchdogTimeout())
                .setKeepPubSubOrder(redisLockProperties.getKeepPubSubOrder());
        config.useSingleServer()
                .setAddress(redisLockProperties.getServerAddress())
                .setConnectionPoolSize(redisLockProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redisLockProperties.getConnectionMinimumIdleSize())
                .setDnsMonitoringInterval(redisLockProperties.getDnsMonitoringInterval())
                .setSubscriptionConnectionMinimumIdleSize(redisLockProperties.getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(redisLockProperties.getSubscriptionConnectionPoolSize())
                .setConnectTimeout(redisLockProperties.getTimeout())
                .setPassword(redisLockProperties.getPassword())
                .setRetryAttempts(redisLockProperties.getRetryAttempts())
                .setRetryInterval(redisLockProperties.getRetryInterval())
                .setKeepAlive(redisLockProperties.getKeepAlive())
                .setConnectTimeout(redisLockProperties.getConnectTimeout())
                .setSslEnableEndpointIdentification(redisLockProperties.getSslEnableEndpointIdentification())
                .setSslKeystore(singleKeyUrl())
                .setSslKeystorePassword(redisLockProperties.getSslKeystorePassword())
                .setSslTruststorePassword(redisLockProperties.getSslTruststorePassword())
                .setSslTruststore(singleUrl())
                .setSslEnableEndpointIdentification(redisLockProperties.getSslEnableEndpointIdentification())
                .setClientName(redisLockProperties.getClientName());
        RedissonClient redisson = Redisson.create(config);
        return redisson.getLock("lock");
    }

    /**
     * 集群模式
     *
     * @return
     */
    @Bean("cluster")
    @ConditionalOnProperty(prefix = "redis.cluster", name = "enable", havingValue = "true", matchIfMissing = false)
    public RLock clusterLock() {
        Config config = new Config();
        config.setNettyThreads(redisClusterLockProperties.getNettyThreads())
                .setLockWatchdogTimeout(redisClusterLockProperties.getLockWatchdogTimeout())
                .setKeepPubSubOrder(redisClusterLockProperties.getKeepPubSubOrder())
                .setThreads(redisLockProperties.getThreads());

        config.useClusterServers()
                // 集群状态扫描间隔时间，单位是毫秒
                .setScanInterval(2000)
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress(redisClusterLockProperties.getAddressList().toArray(new String[redisClusterLockProperties.getAddressList().size()]))
                .setScanInterval(redisClusterLockProperties.getScanInterval())
                .setClientName(redisClusterLockProperties.getClientName())
                .setScanInterval(redisClusterLockProperties.getScanInterval())
                .setCheckSlotsCoverage(redisClusterLockProperties.getCheckSlotsCoverage())
                .setConnectTimeout(redisClusterLockProperties.getConnectTimeout())
                .setIdleConnectionTimeout(redisClusterLockProperties.getIdleConnectionTimeout())
                .setKeepAlive(redisClusterLockProperties.getKeepAlive())
                .setPassword(redisClusterLockProperties.getPassword())
                .setRetryAttempts(redisClusterLockProperties.getRetryAttempts())
                .setRetryInterval(redisClusterLockProperties.getRetryInterval())
                .setSslEnableEndpointIdentification(redisClusterLockProperties.getSslEnableEndpointIdentification())
                .setSslKeystorePassword(redisClusterLockProperties.getSslKeystorePassword())
                .setSubscriptionsPerConnection(redisClusterLockProperties.getSubscriptionsPerConnection())
                .setSslTruststorePassword(redisClusterLockProperties.getSslTruststorePassword())
                .setSslTruststore(url())
                .setSubscriptionConnectionMinimumIdleSize(redisClusterLockProperties.getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(redisClusterLockProperties.getSubscriptionConnectionPoolSize())
                .setSlaveConnectionMinimumIdleSize(redisClusterLockProperties.getSlaveConnectionMinimumIdleSize())
                .setMasterConnectionMinimumIdleSize(redisClusterLockProperties.getMasterConnectionMinimumIdleSize())
                .setMasterConnectionPoolSize(redisClusterLockProperties.getMasterConnectionPoolSize())
                .setTimeout(redisClusterLockProperties.getTimeout())
                .setReconnectionTimeout(redisClusterLockProperties.getReconnectionTimeout())
                .setSslKeystore(keyUrl());
        RedissonClient redisson = Redisson.create(config);
        return redisson.getLock("clusterLock");
    }


    private URL url() {
        try {
            return new URL(redisClusterLockProperties.getSslTruststore());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private URL singleUrl() {
        try {
            return new URL(redisLockProperties.getSslTruststore());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private URL keyUrl() {
        try {
            return new URL(redisClusterLockProperties.getSslKeystore());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private URL singleKeyUrl() {
        try {
            return new URL(redisLockProperties.getSslKeystore());
        } catch (MalformedURLException e) {
            return null;
        }
    }

}
