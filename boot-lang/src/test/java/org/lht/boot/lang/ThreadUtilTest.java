package org.lht.boot.lang;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LiHaitao
 * @description ThreadUtilTest:线程池测试
 * @date 2020/5/21 13:55
 **/
public class ThreadUtilTest {
    //    private ExecutorService executorService = ThreadUtil.newExecutor(10);

    @Test
    public void test01() {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Long start = System.currentTimeMillis();
        int i = 0;
        for (i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    System.out.println("i：" + finalI);
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }

        long end = System.currentTimeMillis();
        System.out.println("cost:" + (end - start));
    }


    @Test
    public void test02() {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final int index = i;

            Long start = System.currentTimeMillis();

            executor.execute(() -> {
                try {
                    System.out.println("sssssssss");
                    Thread.sleep(2 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  " + index);
            });

            long end = System.currentTimeMillis();
            System.out.println("cost1:" + (end - start));
        }


        executor.shutdown();
        long end1 = System.currentTimeMillis();
        //        System.out.println("cost:" + (end1 - start));
    }
}
