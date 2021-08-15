package com.github.codingob.se.juc.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingleThreadExecutor
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            final int num = 3;
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
