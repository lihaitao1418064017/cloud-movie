package org.lht.boot.lang;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author LiHaitao
 * @description ArrayTest:
 * @date 2020/7/3 16:04
 **/
public class ArrayTest {


    @Test
    public void test01() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
        Integer[] a = new Integer[integers.size()];
        Integer[] array = integers.toArray(a);
        System.out.println(array.toString());
    }
}
