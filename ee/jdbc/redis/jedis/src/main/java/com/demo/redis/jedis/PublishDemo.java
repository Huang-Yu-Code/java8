package com.demo.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * 发布/订阅模式-发布
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class PublishDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.publish("redis", "java client send hello");
    }
}
