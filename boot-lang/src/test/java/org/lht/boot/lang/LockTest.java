package org.lht.boot.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

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
//        final ReentrantLock lock=new ReentrantLock();
//        for (int i=0;i<1;i++){
//            lock.lock();
//            try{
//                j++;
//            }finally {
//                lock.unlock();
//            }
//        }
//        Assert.assertEquals(j,100000000);
    }
}
