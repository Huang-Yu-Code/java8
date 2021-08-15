package com.demo.rabbitmq.work.loop;

import com.demo.rabbitmq.Factory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * 工作队列-轮询-消费者示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Consumer {
    /**
     * 队列名称
     */
    private final static String QUEUE = "work-loop";

    public static void main(String[] args) {
        Connection connection;
        Channel channel;
        try {
            connection = Factory.getConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE, false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("工作队列(轮询)---接收的消息为---" + message);
            };
            channel.basicConsume(QUEUE, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
