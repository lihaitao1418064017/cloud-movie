package org.lht.boot.lang.reflect;

import org.junit.jupiter.api.Test;
import org.lht.boot.lang.TestObject;

/**
 * @author LiHaitao
 * @description
 * @date 2020/9/3 14:39
 **/
public class Main1 {

    @Test
    public void test01() {
        Class aClass = TestObject.class;
        aClass.getSuperclass();

    }
}
