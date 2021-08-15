package com.github.codingob.se.juc.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        try {
            final int num = 6;
            for (int i = 0; i < num; i++) {
                final int index = i;
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ", index=" + index));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
