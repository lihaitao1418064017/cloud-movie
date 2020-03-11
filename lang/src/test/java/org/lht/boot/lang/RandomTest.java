package org.lht.boot.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.lang.util.RandomUtils;
import org.mockito.Mockito;

/**
 * @author LiHaitao
 * @description RandomTest:
 * @date 2020/3/11 16:00
 **/
@Slf4j
public class RandomTest {

    @Test
    public void test01() {
        for (int i = 0; i < 10; i++) {
            log.info("grade:  {}", RandomUtils.randomGrade());
        }


    }
}
