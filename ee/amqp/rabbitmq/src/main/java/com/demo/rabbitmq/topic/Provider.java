package com.demo.rabbitmq.topic;

import com.demo.rabbitmq.Factory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 主题模式-生产者示例
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Provider {
    /**
     * 交换机名称
     */
    private final static String EXCHANGE = "demo.topic";
    /**
     * 路由
     */
    private final static String[] ROUTES = {
            "topic.orange.logs",
            "topic.orange.rabbit",
            "topic.logs.rabbit",
            "lazy.orange.logs",
            "lazy.orange.logs.rabbit"
    };
    /**
     * 消息
     */
    private final static String MESSAGE = "Hello RabbitMQ";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = Factory.getConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE, "topic");
            for (String route : ROUTES) {
                channel.basicPublish(EXCHANGE, route, null, (MESSAGE + "---" + route).getBytes());
                System.out.println("发送消息为：" + (MESSAGE + "---" + route));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Factory.close(channel, connection);
        }
    }
}
