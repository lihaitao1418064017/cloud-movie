package org.lht.boot.lang.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author LiHaitao
 * @description
 * @date 2020/8/19 10:52
 **/
public class Main2 {
    /**
     * 我们提供了一个类：
     * <p>
     * public class Foo {
     *   public void first() { print("first"); }
     *   public void second() { print("second"); }
     *   public void third() { print("third"); }
     * }
     * 三个不同的线程将会共用一个 Foo 实例。
     * <p>
     * 线程 A 将会调用 first() 方法
     * 线程 B 将会调用 second() 方法
     * 线程 C 将会调用 third() 方法
     * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3]
     * 输出: "firstsecondthird"
     * 解释:
     * 有三个线程会被异步启动。
     * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
     * 正确的输出是 "firstsecondthird"。
     * 示例 2:
     * <p>
     * 输入: [1,3,2]
     * 输出: "firstsecondthird"
     * 解释:
     * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
     * 正确的输出是 "firstsecondthird"。
     *  
     * <p>
     * 提示：
     * <p>
     * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
     * 你看到的输入格式主要是为了确保测试的全面性。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/print-in-order
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    private CountDownLatch c2;
    private CountDownLatch c3;


    public Main2() {
        c2 = new CountDownLatch(1);
        c3 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        c2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        c2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        c3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        c3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        Main2 main2 = new Main2();
        new Thread(() -> {
            try {
                main2.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                main2.first(() -> System.out.println("first"));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                main2.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }


}
