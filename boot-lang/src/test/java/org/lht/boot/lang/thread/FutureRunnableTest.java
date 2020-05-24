package org.lht.boot.lang.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;



@Slf4j
public class FutureRunnableTest {


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
    public void test01() {
        Callable<String> callable=()->{
            Thread.sleep(3000L);
           return "nihao";
        };

        FutureRunnable<String> futureRunnable=new FutureRunnable<>(callable);
        new Thread(futureRunnable).start();
        String result = futureRunnable.get();
        log.info("reslult:{}",result);

    }


}