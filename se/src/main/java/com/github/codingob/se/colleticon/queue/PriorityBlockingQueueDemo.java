package com.github.codingob.se.colleticon.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        queue.add(1);
        System.out.println(queue);
    }
}
