package com.github.codingob.se.colleticon.map;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(16);
        System.out.println("通话".hashCode());
        System.out.println("重地".hashCode());
        map.put("通话", "one");
        map.put("重地", "two");
        map.put("通话", null);
        map.put(null, "three");
        map.put("4", "four");
        map.remove("4");
        System.out.println(map);
        System.out.println(map.isEmpty());
        System.out.println(map.size());
        System.out.println(map.get("通话"));
        System.out.println(map.containsKey("重地"));
        System.out.println(map.containsValue("one"));
        System.out.println(map.get(null));
        Map<String, String> hashMap = new HashMap<>(16);
        hashMap.put("1", "1");
        hashMap.put("通话", "hashMap");
        map.putAll(hashMap);
        System.out.println(map);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "---" + value);
        }
        System.out.println(map);
        for (String key : map.keySet()) {
            System.out.println(key + "---" + map.get(key));
        }
        map.clear();
    }
}
