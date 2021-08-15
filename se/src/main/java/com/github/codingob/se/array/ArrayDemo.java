package com.github.codingob.se.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ArrayDemo {
    public static void main(String[] args) {
        // 1. 静态初始化
        int[] array1;
        array1 = new int[]{0, 1, 2, 3, 4};
        System.out.println(Arrays.toString(array1));

        // 2. 简化静态初始化
        int[] array2 = {0, 1, 2, 3, 4};
        System.out.println(Arrays.toString(array2));

        // 3. 动态初始化
        int[] array3 = new int[5];
        array3[0] = 0;
        array3[1] = 1;
        array3[2] = 2;
        array3[3] = 3;
        array3[4] = 4;
        System.out.println(Arrays.toString(array3));


        // 4. 遍历数组（for）
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + " ");
        }

        // 5. 遍历数组（for-Each）
        for (int i : array1) {
            System.out.print(i + " ");
        }

        // 6. array转换成List
        IntStream intStream = Arrays.stream(array1);
        Stream<Integer> integerStream = intStream.boxed();
        List<Integer> list = Arrays.asList(integerStream.toArray(Integer[]::new));
        System.out.println(list);

        // 7. List转换成array
        list = new ArrayList<>();
        Integer[] array = list.toArray(new Integer[]{1, 1, 2, 3, 4, 5});
        System.out.println(Arrays.toString(array));
    }
}
