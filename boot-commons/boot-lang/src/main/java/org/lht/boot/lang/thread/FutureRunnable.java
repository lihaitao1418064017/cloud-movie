package org.lht.boot.lang.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * Description: Callable自实现
 *
 * @Author lht
 * @Date 2020/5/23 下午1:49
 **/
public class FutureRunnable<T> implements Runnable {


    private T result;

    private Callable<T> callable;

    private volatile String state;

    private static final String NEW = "NEW";
    private static final String END = "END";

    private static LinkedBlockingQueue<Thread> queue = new LinkedBlockingQueue<>(30);


    public FutureRunnable(Callable<T> callable) {
        state = NEW;
        this.callable = callable;
    }


    public T get() {

        if (END.equals(state)) {
            return result;
        }
        while (NEW.equals(state)) {
            queue.add(Thread.currentThread());
            LockSupport.park();
        }
        return result;
    }


    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            state = END;
        }

        Thread poll = queue.poll();
        while (poll != null) {
            LockSupport.unpark(poll);
            poll = queue.poll();
        }


    }
}
