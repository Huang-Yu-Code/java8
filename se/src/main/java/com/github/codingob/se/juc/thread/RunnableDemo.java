package com.github.codingob.se.juc.thread;

/**
 * Runnable
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class RunnableDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "---实现Runnable接口")).start();
    }
}
