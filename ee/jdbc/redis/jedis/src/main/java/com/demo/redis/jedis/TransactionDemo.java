package com.demo.redis.jedis;

import com.demo.redis.jedis.entity.Entity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class TransactionDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        // 开启事务
        Transaction multi = jedis.multi();

        try {
            String string = new ObjectMapper().writeValueAsString(new Entity(1, "redis"));
            multi.set("name", string);
            int i = 1 / 0;
            multi.set("name1", string);
            // 提交事务
            multi.exec();
        } catch (JsonProcessingException e) {
            // 放弃事务
            multi.discard();
            e.printStackTrace();
        } finally {
            // 释放资源
            jedis.close();
        }

    }
}
