package com.github.codingob.se.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        final int number = 6;
        for (int i = 0; i < number; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("占用资源中..." + temp);
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("释放资源..." + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
