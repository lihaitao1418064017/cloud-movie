package org.lht.boot.lang.reflect;

import org.junit.jupiter.api.Test;

/**
 * @author LiHaitao
 * @description
 * @date 2020/9/3 14:39
 **/
public class Main1 {

    @Test
    public void test01() throws ClassNotFoundException {
        Class<?> name = Class.forName("org.lht.boot.lang.suanfa.Solution");


        System.err.println(name.getSimpleName());
    }
}
