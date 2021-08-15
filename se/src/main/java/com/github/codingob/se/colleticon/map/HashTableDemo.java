package com.github.codingob.se.colleticon.map;

import java.util.Hashtable;
import java.util.Map;

/**
 * HashTable示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class HashTableDemo {
    public static void main(String[] args) {
        Map<String, String> map = new Hashtable<>();
        map.put("hashTable", "key和value不能为null");
        System.out.println(map);
    }
}
