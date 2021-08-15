package com.github.codingob.se.juc.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * scheduledThreadPool
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);

        //定时执行一次的任务，延迟1s后执行
        threadPool.schedule(() -> System.out.println(Thread.currentThread().getName() + ", delay 1s"), 1, TimeUnit.SECONDS);

        //周期性地执行任务，延迟2s后，每3s一次地周期性执行任务
        threadPool.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName() + ", every 3s"), 2, 3, TimeUnit.SECONDS);
    }
}
