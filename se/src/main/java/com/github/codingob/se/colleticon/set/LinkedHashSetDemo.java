package com.github.codingob.se.colleticon.set;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * LinkedHashSet
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class LinkedHashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>();
        // 可以存储null
        set.add(null);
        System.out.println(set);
        set.remove(null);
        System.out.println(set);
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
