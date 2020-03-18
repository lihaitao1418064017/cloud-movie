package org.lht.boot.cache.guava;

import com.google.common.cache.Weigher;

/**
 * @author LiHaitao
 * @description GuavaWeigher:
 * @date 2020/1/17 16:35
 **/
public class GuavaWeigher<K, V> implements Weigher<K, V> {

    @Override
    public int weigh(K k, V v) {
        return 0;
    }

}
