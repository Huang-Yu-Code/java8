package com.github.codingob.se.juc.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * arrayBlockingQueue
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class BlockingQueueDemo {
    private static void exception() {
        final int number = 3;
        Queue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(number);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        // 检查队首元素
        System.out.println(arrayBlockingQueue.peek());

        // 会抛出异常
//        System.out.println(arrayBlockingQueue.add("d"));

        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());

        // 检查队首元素
        System.out.println(arrayBlockingQueue.peek());

        // 会抛出异常
//        System.out.println(arrayBlockingQueue.remove());

    }

    private static void noException() {
        final int number = 3;
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(number);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        // 不会抛出异常,返回false
        System.out.println(arrayBlockingQueue.offer("d"));
        System.out.println(arrayBlockingQueue.element());

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.element());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.element());
        System.out.println(arrayBlockingQueue.poll());

        System.out.println(arrayBlockingQueue.element());
        // 不会抛出异常,返回null
        System.out.println(arrayBlockingQueue.poll());

    }

    private static void waiting() {
        final int number = 3;
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(number);
        try {
            arrayBlockingQueue.put("a");
            arrayBlockingQueue.put("b");
            arrayBlockingQueue.put("c");
            // 一直等待,阻塞
//            arrayBlockingQueue.put("d");
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take());
            // 一直等待,阻塞
//            System.out.println(arrayBlockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void timeOut() {
        final int number = 3;
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(number);

        arrayBlockingQueue.offer("a");
        arrayBlockingQueue.offer("b");
        arrayBlockingQueue.offer("c");
        try {
            arrayBlockingQueue.offer("d", 3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());

        try {
            arrayBlockingQueue.poll(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        exception();
//        noException();
//        waiting();
        timeOut();
    }
}
