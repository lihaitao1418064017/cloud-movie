package org.lht.boot.lang.thread.callable;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.SystemClock;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/5/23 下午5:36
 **/
@Slf4j
public class ExecutorCallableTest {


    private ExecutorService executorService;


    @Before
    public void before() {
        executorService = new ThreadPoolExecutor(10
                , 10
                , 0L
                , TimeUnit.MILLISECONDS
                , new LinkedBlockingQueue<Runnable>(10));

        log.info("start.........");
    }

    @After
    public void after() {
        log.info("end..........");
    }


    @Test
    public void test01() throws ExecutionException, InterruptedException {

        long start = SystemClock.now();
        Callable<String> callable1 = () -> {
            if (true) {
                throw new Exception("aaaaa");
            }
            Thread.sleep(4000);
            return "callable--01";
        };

        Callable<String> callable2 = () -> {

            log.info("start callable2......");
            Thread.sleep(4000);
            return "callable--02";
        };


        Future<String> callableFuture1 = executorService.submit(callable1);

        Future<String> callableFuture2 = executorService.submit(callable2);

        log.info("callable-01={}", callableFuture1.get());
        log.info("callable-02={}", callableFuture2.get());

        long end = SystemClock.now();

        log.info("cost:{} ms", end - start);

        Assert.assertNotNull(callableFuture1.get());
        Assert.assertNotNull(callableFuture2.get());


    }


    @Test
    public void test02() throws ExecutionException, InterruptedException {
        long start = SystemClock.now();

        LinkedBlockingQueue<Future> queue = new LinkedBlockingQueue<>();
        ArrayList<Object> objects = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Callable<String> callable1 = () -> {
                Thread.sleep(1000);
                return "callable--01";
            };
            Future<String> submit = executorService.submit(callable1);
            queue.add(submit);
            //            objects.add(submit.get());
        }
        while (CollectionUtil.isNotEmpty(queue)) {
            Future poll = queue.poll();
            objects.add(poll.get());
        }
        long end = SystemClock.now();
        log.info("objects size:{}", objects.size());
        log.info("cost:{} ms", end - start);

    }


    @Test
    public void test03() {
        long start = SystemClock.now();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    int j = i;
                    executorService.execute(() -> {
                        try {
                            Thread.sleep(1000);

                            log.info("i:{}", j);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //        runnable.run();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = SystemClock.now();
        log.info("cost:{} ms", end - start);

    }

}
