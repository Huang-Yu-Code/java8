package com.github.codingob.se.colleticon.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * ArrayList
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        // add()向集合添加元素,若指定集合元素改变了则返回true
        list.add(1);
        System.out.println(list);

        // get()获取对应索引元素
        System.out.println(list.get(0));

        // remove() 删除指定索引元素
        list.remove(0);
        System.out.println(list);

        // addAll()把集合中的元素全部添加到集合中，若指定集合元素改变返回true
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(2);
        arrayList.add(3);
        list.addAll(arrayList);
        System.out.println(list);

        // containsAll()判断指定集合是否包含集合c的所有元素
        System.out.println(list.containsAll(arrayList));
        System.out.println(list);

        // removeAll()删除指定集合包含集合的元素
        list.removeAll(arrayList);
        System.out.println(list);

        // retainAll()从指定集合中保留包含集合的元素,其他元素则删除
        list.add(0);
        list.addAll(arrayList);
        list.retainAll(arrayList);
        System.out.println(list);

        // clear()清空所有集合元素
        list.clear();
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
