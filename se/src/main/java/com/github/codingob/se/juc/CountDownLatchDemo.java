package com.github.codingob.se.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        final int threadNum = 6;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName());
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("close");
    }
}
