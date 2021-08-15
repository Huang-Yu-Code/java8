package com.github.codingob.se.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock-读写锁
 * 写锁:一次只能有一个线程占有
 * 读锁:多个线程可以同时占有
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
//        Cache cache = new Cache();
        CacheLock cache=new CacheLock();
        final int number = 5;
        for (int i = 0; i < number; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.put(temp + "", temp);
            }).start();
        }
        for (int i = 0; i < number; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.get(temp + "");
            }).start();
        }
    }
}

class Cache {
    private final Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        System.out.println(key + "---写入-- " + key);
        map.put(key, value);
        System.out.println(key + "---写入完成");
    }

    public void get(String key) {
        System.out.println(key + "---读取--" + key);
        Object o = map.get(key);
        System.out.println(key + "---读取完成");
    }
}

class CacheLock{
    private final Map<String, Object> map = new HashMap<>();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(key + "---写入-- " + key);
            map.put(key, value);
            System.out.println(key + "---写入完成");
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(key + "---读取--" + key);
            Object o = map.get(key);
            System.out.println(key + "---读取完成");
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
