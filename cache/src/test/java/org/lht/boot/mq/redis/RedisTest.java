package org.lht.boot.mq.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.lht.boot.cache.entity.User;
import org.lht.boot.cache.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiHaitao
 * @description RedisTest:
 * @date 2020/1/17 15:20
 **/
@Slf4j
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set() {
        User user = new User();
        user.setAge(12);
        user.setName("lihaitao");
        redisUtil.set("li", user);
        User sdf = (User) redisUtil.get("li");
        log.info("user:{}", sdf);
    }
}
