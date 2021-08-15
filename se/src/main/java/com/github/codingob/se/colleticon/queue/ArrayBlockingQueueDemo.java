package com.github.codingob.se.colleticon.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(queue);
        queue.add(5);
    }
}
