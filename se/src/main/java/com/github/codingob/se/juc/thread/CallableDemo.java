package com.github.codingob.se.juc.thread;

import java.util.concurrent.FutureTask;

/**
 * Callable
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class CallableDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "---Callable接口");
            final int number = 101;
            int sum = 0;
            for (int i = 0; i < number; i++) {
                sum += i;
            }
            return sum;
        });
        new Thread(futureTask).start();
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
