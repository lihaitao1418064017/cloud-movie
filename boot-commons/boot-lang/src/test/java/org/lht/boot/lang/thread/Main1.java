package org.lht.boot.lang.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static cn.hutool.core.lang.Console.print;

/**
 * @author LiHaitao
 * @description
 * @date 2020/8/19 10:44
 **/
public class Main1 {
    /**
     * 我们提供一个类：
     * <p>
     * class FooBar {
     * public void foo() {
     *     for (int i = 0; i < n; i++) {
     *       print("foo");
     *     }
     * }
     * <p>
     * public void bar() {
     *     for (int i = 0; i < n; i++) {
     *       print("bar");
     *     }
     * }
     * }
     * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
     * <p>
     * 请设计修改程序，以确保 "foobar" 被输出 n 次。
     * <p>
     *  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        Condition condition = reentrantLock.newCondition();

        FooBar fooBar = new FooBar(reentrantLock, condition);
        new Thread(fooBar::foo, "fpp").start();
        new Thread(fooBar::bar, "barr").start();
    }

    static class FooBar {

        private int n = 10;
        ReentrantLock reentrantLock;

        Condition condition;

        public FooBar(ReentrantLock lock, Condition condition) {
            this.reentrantLock = lock;
            this.condition = condition;
        }

        public void foo() {
            for (int i = 0; i < n; i++) {
                new Thread(()->{
                    reentrantLock.lock();
                    print("foo");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("sdfsdf");
                    reentrantLock.unlock();
                },"foo print").start();
            }
        }

        public void bar() {
            for (int i = 0; i < n; i++) {

                new Thread(()->{
                    reentrantLock.lock();
                    print("bar");
//                    condition.signal();
                },"bar print").start();

            }
        }
    }

}
