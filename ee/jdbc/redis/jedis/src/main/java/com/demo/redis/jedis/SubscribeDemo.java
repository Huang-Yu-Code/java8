package com.demo.redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 发布/订阅模式-订阅
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SubscribeDemo {
    static class Listener extends JedisPubSub {
        private final Logger logger = LoggerFactory.getLogger(Listener.class);

        @Override
        public void onMessage(String channel, String message) {
            logger.info("订阅频道: " + channel + " - 消息: " + message);
        }

        @Override
        public void onPMessage(String pattern, String channel, String message) {
            System.out.println(pattern);
            System.out.println(channel);
            System.out.println(message);
        }

        @Override
        public void onSubscribe(String channel, int subscribedChannels) {
            logger.info("订阅频道: " + channel + " - 订阅数: " + subscribedChannels);
        }

    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.subscribe(new Listener(), "redis");
    }

}

