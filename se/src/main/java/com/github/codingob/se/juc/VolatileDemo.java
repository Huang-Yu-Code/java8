package com.github.codingob.se.juc;

import java.util.concurrent.atomic.AtomicInteger;

/** volatile
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class VolatileDemo {
    /**
     * 不加volatile会死循环
     */
    private volatile static int num = 0;
    private volatile static AtomicInteger atomicInteger;

    public static void main(String[] args) {
        atomicInteger = new AtomicInteger();
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();

        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int k = 0; k < 1000; k++) {
                    num++;
                    atomicInteger.getAndIncrement();
                }
            }).start();
        }
        System.out.println("num: " + num);
        while (Thread.activeCount() > 3) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "---num: " + num);
        System.out.println(Thread.currentThread().getName() + "---ATOMIC: " + atomicInteger.get());
    }
}
