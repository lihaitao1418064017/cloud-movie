package org.lht.boot.cache.redis;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiHaitao
 * @description RedisTest:
 * @date 2020/4/26 9:59
 **/
@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test01() {

        redisUtil.get("sdf");
    }

    @Test
    public void test02() {
        ArrayList<String> strings = Lists.newArrayList("01", "02", "03");
        redisUtil.lSet("key", strings);

        List list = redisUtil.lGet("key", 0, -1);
        log.info("list:{}", list);
    }
}
