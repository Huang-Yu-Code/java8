package com.demo.rabbitmq.work.fair;

import com.demo.rabbitmq.Factory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 工作队列-公平-消费者示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Consumer {
    /**
     * 队列名称
     */
    private final static String QUEUE = "work-fair";

    public static void main(String[] args) {
        Connection connection;
        Channel channel;
        try {
            connection = Factory.getConnection();
            channel = connection.createChannel();
            channel.basicQos(1);
            channel.queueDeclare(QUEUE, false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("工作队列(公平)---接收的消息为---" + message);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume(QUEUE, false, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
