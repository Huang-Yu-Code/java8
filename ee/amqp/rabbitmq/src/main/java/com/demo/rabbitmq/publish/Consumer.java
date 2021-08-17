package com.demo.rabbitmq.publish;

import com.demo.rabbitmq.Factory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * 发布/订阅模式-消费者示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Consumer {
    /**
     * 交换机名称
     */
    private final static String EXCHANGE = "demo.fanout";

    public static void main(String[] args) {
        Connection connection;
        Channel channel;
        try {
            connection = Factory.getConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE, "fanout");
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE, "");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("发布/订阅---接收的消息为---" + message);
            };
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
