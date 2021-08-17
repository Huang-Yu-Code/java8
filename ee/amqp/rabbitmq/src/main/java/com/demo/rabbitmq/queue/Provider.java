package com.demo.rabbitmq.queue;

import com.demo.rabbitmq.Factory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 简单队列-生产者示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Provider {
    /**
     * 队列名称
     */
    private final static String QUEUE = "queue";
    /**
     * 发送的消息
     */
    private final static String MESSAGE = "Hello RabbitMQ";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = Factory.getConnection();
            channel = connection.createChannel();
            // 队列名称,是否持久化,是否自动删除队列，是否排外
            channel.queueDeclare(QUEUE, false, false, false, null);
            channel.basicPublish("", QUEUE, null, MESSAGE.getBytes());
            System.out.println("发送消息为：" + QUEUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Factory.close(channel, connection);
        }
    }
}
