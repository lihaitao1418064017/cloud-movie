package org.lht.boot.mq.guava;

import com.google.common.cache.CacheLoader;

/**
 * @author LiHaitao
 * @description GuavaCacheLoader:
 * @date 2020/1/17 16:26
 **/
public class GuavaCacheLoader<K, V> extends CacheLoader<K, V> {

    @Override
    public V load(K k) throws Exception {
        return (V) "test";
    }

}
