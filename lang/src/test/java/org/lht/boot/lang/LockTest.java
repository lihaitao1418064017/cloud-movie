package org.lht.boot.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/11 7:27 PM
 **/
public class LockTest {
    public static int j=0;

    @Test
    public void test01() throws InterruptedException {
        for (int i=0;i<100000000;i++){
//            Thread.sleep(10);
            j++;

        }
        Assert.assertEquals(j,100000000);
    }
}
