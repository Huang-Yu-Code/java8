package com.github.codingob.se.colleticon.map;

import java.util.TreeMap;

/**
 * TreeMap示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("null",null);
        map.put("1",null);
        System.out.println(map);
    }
}
