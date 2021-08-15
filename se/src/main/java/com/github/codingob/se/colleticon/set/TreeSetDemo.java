package com.github.codingob.se.colleticon.set;

import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        // 不能存储null
        set.add("不能存放null");
        System.out.println(set);
        // 自然排序
        set.add("5");
        set.add("4");
        set.add("2");
        set.add("1");
        set.add("3");
        System.out.println(set);
        for (String value : set) {
            System.out.println(value);
        }
    }
}
