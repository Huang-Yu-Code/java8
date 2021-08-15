package com.github.codingob.se.string;

import java.util.concurrent.CyclicBarrier;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class StringDemo {
    public static void main(String[] args) {
        String string = "abc";
        System.out.println(string);
        string += "def";
        System.out.println(string);

    }
}

class StringBufferDemo {
    public static void main(String[] args) {
        StringBuffer string = new StringBuffer();
        // 多线程,安全
        int threadNum = 10;
        int loopNum = 1000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, () -> System.out.println(string.length()));
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < loopNum; j++) {
                    string.append("a");
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

class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder string = new StringBuilder();
        // 多线程,不安全
        int threadNum = 10;
        int loopNum = 1000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, () -> System.out.println(string.length()));
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < loopNum; j++) {
                    string.append("a");
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
