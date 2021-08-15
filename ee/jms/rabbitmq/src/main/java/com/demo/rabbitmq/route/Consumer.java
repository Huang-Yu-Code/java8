package com.demo.rabbitmq.route;

import com.demo.rabbitmq.Factory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * 路由模式-消费者示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Consumer {
    /**
     * 交换机名称
     */
    private final static String EXCHANGE = "demo.direct";
    /**
     * 精确路由
     */
    private final static String[] ROUTES = {"info", "warn", "error"};

    public static void main(String[] args) {
        Connection connection;
        Channel channel;
        try {
            connection = Factory.getConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE, "direct");
            String queue = channel.queueDeclare().getQueue();
            for (String route : ROUTES) {
                channel.queueBind(queue, EXCHANGE, route);
            }
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("路由模式---接收的消息为---" + message);
            };
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
