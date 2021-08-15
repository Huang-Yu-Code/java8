package com.github.codingob.se.juc.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * 多线程下Map
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class MapDemo {
    private static void put(Map<String, String> map,String type) {
        final int threadNum = 10;
        final int loopNum = 1000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, () -> System.out.println(type + "---" + map.size()));
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < loopNum; j++) {
                    map.put(UUID.randomUUID().toString(),"a");
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
        Map<String, String> hashMap = new HashMap<>(16);
        put(hashMap,"hashMap");

        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>(16));
        put(synchronizedMap,"synchronizedMap");

        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>(16);
        put(concurrentHashMap,"concurrentHashMap");
    }
}
