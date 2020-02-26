package org.lht.boot.mq.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

/**
 * @author LiHaitao
 * @description MyCache:
 * @date 2020/1/19 15:18
 **/
@Cacheable
public class MyCache implements Cache {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Nullable
    @Override
    public ValueWrapper get(Object o) {
        return null;
    }

    @Nullable
    @Override
    public <T> T get(Object o, @Nullable Class<T> aClass) {
        return null;
    }

    @Nullable
    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object o, @Nullable Object o1) {

    }

    @Override
    public void evict(Object o) {

    }

    @Override
    public void clear() {

    }
}
