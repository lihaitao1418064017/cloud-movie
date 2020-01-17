package org.lht.boot.cache.redis;

import org.junit.jupiter.api.Test;
import org.lht.boot.cache.guava.GuavaCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void test() {
        cacheUtil.addOne("sdf");

        System.out.println(cacheUtil.exists("sdf"));
    }
}
