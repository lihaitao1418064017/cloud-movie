package org.lht.boot.security.server.dao;

import org.junit.jupiter.api.Test;
import org.lht.boot.security.server.domain.entity.SystemBroadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/28 11:27
 **/
@SpringBootTest
public class SystemBroadcastTest {

    @Autowired
    private SystemBroadcastDao dao;

    @Test
    public void test01() {
        SystemBroadcast systemBroadcast = new SystemBroadcast();
        systemBroadcast.setCreateTime(10000000000L);
        systemBroadcast.setId("6504467c7ac24eb7b9a73d92ad4e5881");
        dao.update(systemBroadcast);
    }

    @Test
    public void test02() {
        SystemBroadcast systemBroadcast = new SystemBroadcast();
        systemBroadcast.setCreateTime(10000000000L);
        systemBroadcast.setId("6504467c7ac24eb7b9a73d92ad4e5881");
        dao.patch(systemBroadcast);
    }
}
