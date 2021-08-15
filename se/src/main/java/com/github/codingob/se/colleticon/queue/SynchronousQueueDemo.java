package com.github.codingob.se.colleticon.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        queue.add(1);
        System.out.println(queue);
    }
}
