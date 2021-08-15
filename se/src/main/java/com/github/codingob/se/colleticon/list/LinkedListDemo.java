package com.github.codingob.se.colleticon.list;

import java.util.LinkedList;
import java.util.concurrent.CyclicBarrier;

/**
 * LinkedList
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);
        System.out.println(list);

        // 多线程,不安全
        int threadNum = 10;
        int loopNum = 1000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, () -> System.out.println(list.size()));
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < loopNum; j++) {
                    list.add(j);
                }
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
