package org.lht.boot.lang;

import org.junit.jupiter.api.Test;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/22 10:07
 **/
public class Main {


    @Test
    public void test01() {

        for (long r = -100; r < 100000000000000L; r++) {
            try {
                if ((r * r * 49) + (95 * r) == 7) {
                    System.out.println(r);
                }
            } catch (Exception e) {

            }

        }

        //        long r = 715827898;
        //        long a = (r * r * 49) + (95 * r) - 7;
        //        System.out.println(a);
    }
}
