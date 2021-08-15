package com.github.codingob.se.juc.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CyclicBarrier;

/**
 * 多线程下Set示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */

public class SetDemo {
    private static void add(Set<String> set, String type) {
        final int threadNum = 10;
        final int loopNum = 1000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, () -> System.out.println(type + "---" + set.size()));
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < loopNum; j++) {
                    set.add(UUID.randomUUID().toString());
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
        Set<String> hashSet = new HashSet<>();
        add(hashSet, "hashSet");

        Set<String> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
        add(synchronizedSet, "synchronizedSet");

        Set<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        add(copyOnWriteArraySet,"copyOnWriteArraySet");
    }
}
