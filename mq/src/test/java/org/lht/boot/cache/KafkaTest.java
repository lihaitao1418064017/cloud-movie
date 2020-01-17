package org.lht.boot.cache;

import org.junit.jupiter.api.Test;
import org.lht.boot.cache.common.entity.User;
import org.lht.boot.cache.producer.KafkaListenableFutureCallback;
import org.lht.boot.cache.producer.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiHaitao
 * @description KafkaTest:
 * @date 2020/1/16 14:16
 **/
@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaSender<String, User> kafkaSender;

    @Test
    void sendTest() {
        User user = new User();
        user.setAge(31);
        user.setName("lihaitao");
        kafkaSender.sendByJsonStr("order", user).addCallback(new KafkaListenableFutureCallback<>());
    }
}
