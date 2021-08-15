package com.demo.redis.jedis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class RedisTest {
    private static final Jedis jedis;

    static {
        jedis = new Jedis("localhost", 6379);
    }

    @Test
    void ping() {
        String ping = jedis.ping();
        Assertions.assertEquals("PONG", ping);
    }

    @Test
    void flushDB() {
        String flushDB = jedis.flushDB();
        Assertions.assertEquals("OK", flushDB);
    }

    @Test
    void flushAll(){
        String flushAll = jedis.flushAll();
        Assertions.assertEquals("OK", flushAll);
    }

    @Test
    void set() {
        jedis.set("redis", "Hello Redis");
    }

    @Test
    void get() {
        String name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    void append(){
        Long name = jedis.append("name", "is 2B");
    }
}
