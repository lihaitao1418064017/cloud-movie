package org.lht.boot.cache.guava;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * @author LiHaitao
 * @description GuavaRemovalListener:
 * @date 2020/1/17 16:36
 **/
public class GuavaRemovalListener<K, V> implements RemovalListener<K, V> {

    @Override
    public void onRemoval(RemovalNotification removalNotification) {

    }
}
