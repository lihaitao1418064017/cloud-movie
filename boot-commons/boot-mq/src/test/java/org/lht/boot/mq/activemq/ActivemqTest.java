package org.lht.boot.mq.activemq;

import org.junit.jupiter.api.Test;
import org.lht.boot.mq.activemq.producer.ActivemqSender;
import org.lht.boot.mq.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiHaitao
 * @description ActivemqTest:
 * @date 2020/3/2 9:43
 **/
@SpringBootTest
public class ActivemqTest {

    @Autowired
    private ActivemqSender<User> activemqSender;

    @Test
    public void testSend() {
        User user = new User();
        user.setAge(18);
        user.setName("lihaitao");
        activemqSender.sendToTopic("topicUser", user);


    }
}
