package com.github.codingob.se.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic原子类
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class AtomicDemo {
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        for (int j = 0; j < 20; j++) {
            new Thread(() -> {
                for (int k = 0; k < 10; k++) {
                    atomicInteger.getAndIncrement();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "---num: " + atomicInteger);
    }
}
