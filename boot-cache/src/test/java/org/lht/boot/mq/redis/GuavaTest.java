package org.lht.boot.mq.redis;

import org.junit.Test;
import org.lht.boot.cache.guava.GuavaCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

/**
 * @author LiHaitao
 * @description GuavaTest:
 * @date 2020/1/17 16:01
 **/
@SpringBootTest
public class GuavaTest {

    @Autowired
    private GuavaCacheUtil<String, String> cacheUtil;

    @Test
    public void test() throws ExecutionException {
        cacheUtil.put("sdf", "sdf");
        System.err.println(cacheUtil.getOne("sdf"));
        cacheUtil.deleteOne("sdf");
        System.err.println(cacheUtil.getOne("sdf"));

    }
}
