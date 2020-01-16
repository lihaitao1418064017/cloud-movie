package org.lht.boot.web.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.web.domain.entity.User;

import java.util.Map;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/1/11 6:52 PM
 **/
@Slf4j
public class UtilTest {


    /**
     * MapUtil测试
     */

    @Test
    public void testMap() {
        User user = new User();
        user.setName("李强");
        user.setAge(34);
        Map map = BeanUtils.objectToMap(user, false);
        log.info("map:{}", map);
    }
}
