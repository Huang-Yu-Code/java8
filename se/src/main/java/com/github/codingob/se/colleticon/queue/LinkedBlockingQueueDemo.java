package com.github.codingob.se.colleticon.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(4);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(queue);
        queue.add(5);
    }
}
