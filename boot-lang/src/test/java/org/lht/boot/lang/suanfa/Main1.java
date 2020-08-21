package org.lht.boot.lang.suanfa;

import org.junit.jupiter.api.Test;

/**
 * @author LiHaitao
 * @description
 * @date 2020/8/19 17:32
 **/
public class Main1 {

    @Test
    public void test01() {
        int n = 10000;
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (i == 2) {
                count++;
                continue;
            }
            int j;
            //            if (i % 2 != 0 && i % 3 != 0 && i % 5 != 0 && i % 7 != 0 && i % 11 != 0) {
            //                count++;
            //            }
            int k;
            for (j = 2, k = i / 2 + 1; j <= i / 2 + 1 && k >= 2; j++, k--) {
                if (i % j == 0 || i % k == 0) {
                    break;
                }
            }
            if (j == i / 2 + 1 + 1) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println(count);
    }


    @Test
    public void test02() {
        for (int i = 2; i < 6973 / 2; i++) {
            if (6973 % i == 0) {
                System.out.println(i);
            }
        }
    }
}
