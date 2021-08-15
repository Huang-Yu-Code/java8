package com.demo.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SetDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
    }
}
