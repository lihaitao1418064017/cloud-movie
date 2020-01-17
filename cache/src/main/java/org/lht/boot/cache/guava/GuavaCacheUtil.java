package org.lht.boot.cache.guava;

import com.google.common.base.Preconditions;
import com.google.common.cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author LiHaitao
 * @description GuavaCacheUtil:CacheUtil工具类
 * @date 2020/1/17 15:57
 **/
@Component
public class GuavaCacheUtil<K, V> {

    @Autowired
    private GuavaCacheProperties cacheProperties;

    /**
     * 缓存对象
     */
    private LoadingCache<K, V> cache;

    /**
     * 默认缓存最大容量
     */
    public static final int DEFAULT_MAXIMUM_SIZE = 16;

    /**
     * 默认刷新时间
     */
    public static final int DEFAULT_REFRESH_TIME = 1;

    /**
     * 默认过期时间
     */
    public static final int DEFAULT_EXPIRE_TIME = 1;

    /**
     * 基于权重的回收中的默认最大权重
     */
    public static final int DEFAULT_MAXIMUM_WEIGHT = 1000;

    /**
     * 默认时间单位：秒
     */
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 默认过期方式：不自动过期
     */
    public static final ExpireMethod DEFAULT_EXPIRE_METHOD = ExpireMethod.NO_EXPIRE;

    /**
     * 默认引用方式：强引用
     */
    public static final ReferenceMethod DEFAULT_REFERENCE_METHOD = ReferenceMethod.DEFAULT;

    public GuavaCacheUtil(CacheLoader<K, V> cacheLoader
            , Weigher<K, V> weigher
            , RemovalListener<K, V> removalListener
            , long maximumWeight
            , int maximumSize
            , long refreshTime
            , TimeUnit refreshTimeUnit
            , long expireTime
            , TimeUnit expireTimeUnit
            , String expireMethodStr
            , String keyReferenceMethodStr
            , String valueReferenceMethodStr) {
        Preconditions.checkNotNull(cacheLoader, "cache loader must not be null");
        ExpireMethod expireMethod = getExpireMethod(expireMethodStr);
        ReferenceMethod keyReferenceMethod = getReferenceMethod(keyReferenceMethodStr);
        ReferenceMethod valueReferenceMethod = getReferenceMethod(valueReferenceMethodStr);
        maximumSize = maximumSize <= 0 ? DEFAULT_MAXIMUM_SIZE : maximumSize;
        refreshTime = refreshTime < 0 ? DEFAULT_REFRESH_TIME : refreshTime;
        expireTime = expireTime < 0 ? DEFAULT_EXPIRE_TIME : expireTime;
        maximumWeight = maximumWeight <= 0 ? DEFAULT_MAXIMUM_WEIGHT : maximumWeight;
        refreshTimeUnit = refreshTimeUnit == null ? DEFAULT_TIME_UNIT : refreshTimeUnit;
        expireTimeUnit = expireTimeUnit == null ? DEFAULT_TIME_UNIT : expireTimeUnit;
        expireMethod = expireMethod == null ? DEFAULT_EXPIRE_METHOD : expireMethod;
        keyReferenceMethod = (keyReferenceMethod == null || keyReferenceMethod == ReferenceMethod.SOFT)
                ? DEFAULT_REFERENCE_METHOD : keyReferenceMethod;
        valueReferenceMethod = valueReferenceMethod == null ? DEFAULT_REFERENCE_METHOD : valueReferenceMethod;
        //初始化本地缓存
        init(cacheLoader, weigher, removalListener, maximumWeight, maximumSize, refreshTime, refreshTimeUnit,
                expireTime, expireTimeUnit, expireMethod, keyReferenceMethod, valueReferenceMethod);
    }

    public GuavaCacheUtil() {

    }

    public GuavaCacheUtil build(TimeUnit refreshTimeUnit, TimeUnit expireTimeUnit) {
        return new GuavaCacheUtil<K, V>(new GuavaCacheLoader(),
                new GuavaWeigher()
                , new GuavaRemovalListener()
                , cacheProperties.getMaxWeight()
                , cacheProperties.getMaxSize()
                , cacheProperties.getRefreshTime()
                , TimeUnit.SECONDS
                , cacheProperties.getExpireTime()
                , TimeUnit.SECONDS
                , cacheProperties.getExpireMethod()
                , cacheProperties.getKeyReferenceMethod()
                , cacheProperties.getValueReferenceMethod());
    }

    /**
     * 初始化本地缓存
     */
    public void init(CacheLoader<K, V> cacheLoader
            , Weigher<K, V> weigher
            , RemovalListener<K, V> removalListener
            , long maximumWeight
            , long maximumSize
            , long refreshTime
            , TimeUnit refreshTimeUnit
            , long expireTime
            , TimeUnit expireTimeUnit
            , ExpireMethod expireMethod
            , ReferenceMethod keyReferenceMethod
            , ReferenceMethod valueReferenceMethod) {
        CacheBuilder cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder = weigher != null ? cacheBuilder.weigher(weigher).maximumWeight(maximumWeight) : cacheBuilder;
        cacheBuilder = removalListener != null ? cacheBuilder.removalListener(removalListener) : cacheBuilder;
        cacheBuilder = cacheBuilder.maximumSize(maximumSize).refreshAfterWrite(refreshTime, refreshTimeUnit);

        cacheBuilder = expireMethod == ExpireMethod.NO_EXPIRE ? cacheBuilder
                : (expireMethod == ExpireMethod.AFTER_WRITE ? cacheBuilder.expireAfterWrite(expireTime, expireTimeUnit)
                : cacheBuilder.expireAfterAccess(expireTime, expireTimeUnit));
        cacheBuilder = keyReferenceMethod == ReferenceMethod.DEFAULT ? cacheBuilder : cacheBuilder.weakKeys();
        cacheBuilder = valueReferenceMethod == ReferenceMethod.DEFAULT ? cacheBuilder
                : (valueReferenceMethod == ReferenceMethod.SOFT ? cacheBuilder.softValues()
                : cacheBuilder.weakValues());
        cache = cacheBuilder.build(cacheLoader);
    }

    /**
     * 添加一个键值对
     *
     * @param key
     */
    public void addOne(K key) {
        cache.refresh(key);
    }

    /**
     * 添加多个键值对
     *
     * @param keys
     */
    public void addAll(Iterable<K> keys) {
        for (K key : keys) {
            cache.refresh(key);
        }
    }

    /**
     * 删除一个键值对
     *
     * @param key
     * @return 被删除的键值对的值
     * @throws ExecutionException
     */
    public V deleteOne(K key) throws ExecutionException {
        V value = getOne(key);
        cache.invalidate(key);
        return value;
    }

    /**
     * 删除多个键值对
     *
     * @param keys
     */
    public void deleteAll(Iterable<K> keys) {
        cache.invalidateAll(keys);
    }

    /**
     * 清空缓存
     */
    public void deleteAll() {
        cache.invalidateAll();
    }

    /**
     * 得到某个键对应的值
     *
     * @param key
     * @return
     * @throws ExecutionException
     */
    public V getOne(K key) throws ExecutionException {
        return cache.get(key);
    }

    /**
     * 判断某个键值对是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(K key) {
        return cache.asMap().containsKey(key);
    }

    /**
     * 手动像缓存里添加一个键值对
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public String toString() {
        return cache.asMap().toString();
    }

    private ExpireMethod getExpireMethod(String expireMethod) {
        if (expireMethod == null) {
            return ExpireMethod.NO_EXPIRE;
        }
        switch (expireMethod) {
            case "no_expire":
                return ExpireMethod.NO_EXPIRE;
            case "after_write":
                return ExpireMethod.AFTER_WRITE;
            case "after_access":
                return ExpireMethod.AFTER_ACCESS;
            default:
                return ExpireMethod.NO_EXPIRE;

        }
    }

    private ReferenceMethod getReferenceMethod(String referenceMethod) {
        if (referenceMethod == null) {
            return ReferenceMethod.DEFAULT;
        }
        switch (referenceMethod) {
            case "default":
                return ReferenceMethod.DEFAULT;
            case "weak":
                return ReferenceMethod.WEAK;
            case "soft":
                return ReferenceMethod.SOFT;
            default:
                return ReferenceMethod.DEFAULT;
        }
    }

    /**
     * 自动回收方式
     */
    public enum ExpireMethod {
        // 不自动回收
        NO_EXPIRE,//no_expire

        // 缓存项在给定时间内没有被写访问（创建或覆盖），则回收
        AFTER_WRITE,//after_write

        // 缓存项在给定时间内没有被读/写访问，则回收
        AFTER_ACCESS//after_access
    }

    /**
     * 键值对象引用方式
     */
    public enum ReferenceMethod {
        // 默认强引用方式，不会被垃圾回收
        DEFAULT,//default

        // 弱引用方式，会被垃圾回收
        WEAK,//weak

        // 软引用方式，在内存很多的时候会被垃圾回收
        SOFT//soft
    }
}