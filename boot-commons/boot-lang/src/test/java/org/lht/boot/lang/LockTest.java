package org.lht.boot.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/11 7:27 PM
 **/
@Slf4j
public class LockTest {
    public static int j = 0;
    Lock lock;

    @Before
    public void before() {
        lock = new ReentrantLock();

    }

    @Test
    public void test01() throws InterruptedException {
        for (int i = 0; i < 100000000; i++) {
            lock.lock();
            try {
                j++;
            } finally {
                lock.unlock();
            }
        }
        Assert.assertEquals(j, 100000000);
    }

    @Test
    public void test02() throws InterruptedException {
        new Thread(()->{
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }


        },"线程-A").start();
        new Thread(()->{
            try {
                if (lock.tryLock(2,TimeUnit.SECONDS)){
                    log.info("获取到了锁");
                }
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        },"线程-B").start();
        Thread.sleep(5000);
        System.out.println(lock.tryLock());
    }
}
