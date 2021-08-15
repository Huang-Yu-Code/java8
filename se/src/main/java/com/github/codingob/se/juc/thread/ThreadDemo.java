package com.github.codingob.se.juc.thread;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ThreadDemo extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---继承Thread类");
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        new ThreadDemo().start();
    }

}
