package com.github.codingob.se.juc.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CachedThreadPool
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            final int num = 100;
            for (int i = 0; i < num; i++) {
                final int temp = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + ", index=" + temp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
