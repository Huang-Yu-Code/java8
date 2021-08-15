package com.github.codingob.se.juc.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 * 多线程下List示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ListDemo {
    private static void add(List<String> list, String type) {
        final int threadNum = 10;
        final int loopNum = 1000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, () -> System.out.println(type + "---" + list.size()));
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < loopNum; j++) {
                    list.add("1");
                }
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        add(arrayList,"arrayList");

        Vector<String> vector = new Vector<>();
        add(vector,"vector");

        // synchronized
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        add(synchronizedList,"synchronizedList");

        // 写入时复制，COW
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        add(copyOnWriteArrayList,"copyOnWriteArrayList");
    }
}

