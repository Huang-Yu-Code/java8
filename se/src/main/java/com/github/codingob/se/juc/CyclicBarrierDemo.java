package com.github.codingob.se.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        final int threadNum = 7;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, () -> {
            System.out.println("cyclicBarrier执行成功");
        });
        for (int i = 0; i < threadNum; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "---" + temp);
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
