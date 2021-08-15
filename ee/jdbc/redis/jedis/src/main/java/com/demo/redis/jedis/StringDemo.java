package com.demo.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class StringDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        // ping
        String ping = jedis.ping();
        System.out.println(ping);

        // flushDB()清空数据库
        jedis.flushDB();

        // 设置一个值
        String set = jedis.set("name", "redis");
        System.out.println(set);

        // 获取一个值
        String name = jedis.get("name");
        System.out.println(name);

        // 添加值
        jedis.append("name", " is very good!");
        System.out.println(jedis.get("name"));

        // 删除一个值
        jedis.del("name");
        System.out.println(jedis.get("name"));

        // 设置多个值
        String mset = jedis.mset("name1", "value1", "name2", "value2", "name3", "value3");
        System.out.println(mset);

        // 获取多个值
        List<String> mget = jedis.mget("name1", "name2", "name3");
        System.out.println(mget);

        // 删除多个值
        Long del1 = jedis.del("name1", "name2");
        System.out.println(del1);

        // 指定的 key 不存在时，为 key 设置指定的值
        jedis.setnx("key1", "value1");
        System.out.println(jedis.get("key1"));
        jedis.setnx("key1", "value2");
        System.out.println(jedis.get("key1"));

        // 为指定的 key 设置值及其过期时间。如果 key 已经存在将会替换旧的值。
        jedis.setex("key2", 60L, "value2");
        System.out.println(jedis.get("key2"));
        // 秒为单位返回 key 的剩余过期时间
        System.out.println(jedis.ttl("key2"));

        // 清空所有值
        jedis.flushAll();

        // 关闭
        jedis.close();
    }
}
