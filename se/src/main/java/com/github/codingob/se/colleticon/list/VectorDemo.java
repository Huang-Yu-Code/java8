package com.github.codingob.se.colleticon.list;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CyclicBarrier;

/**
 * Vector
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class VectorDemo {

    public static void main(String[] args) {
        List<Integer> list = new Vector<>();
        list.add(0);
        System.out.println(list);
        list.remove(0);

        // 多线程,安全
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
