package com.demo.rabbitmq.topic;

import com.demo.rabbitmq.Factory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * 主题模式-消费者示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Consumer {
    /**
     * 交换机名称
     */
    private final static String EXCHANGE = "demo.topic";
    /**
     * 模糊匹配规则
     */
    private final static String ROUTE = "*.orange.*";

    public static void main(String[] args) {
        Connection connection;
        Channel channel;
        try {
            connection = Factory.getConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE, "topic");
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE, ROUTE);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("主题模式---接收的消息为---" + message);
            };
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
