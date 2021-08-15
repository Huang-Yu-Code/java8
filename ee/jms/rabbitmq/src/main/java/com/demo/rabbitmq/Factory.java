package com.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * RabbitMQ工厂
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Factory {
    /**
     * 初始化连接工厂
     */
    private final static ConnectionFactory FACTORY = new ConnectionFactory();
    /**
     * 连接信息
     */
    private final static String LOCATION = "rabbitmq.properties";

    // 配置连接信息
    static {
        Properties properties = new Properties();
        try {
            properties.load(Factory.class.getResourceAsStream(LOCATION));
            FACTORY.setHost(properties.getProperty("rabbitmq.host"));
            FACTORY.setPort(Integer.parseInt(properties.getProperty("rabbitmq.port")));
            FACTORY.setUsername(properties.getProperty("rabbitmq.username"));
            FACTORY.setPassword(properties.getProperty("rabbitmq.password"));
            FACTORY.setVirtualHost(properties.getProperty("rabbitmq.virtual-host"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取RabbitMQ连接
     *
     * @return 连接
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = FACTORY.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放资源
     *
     * @param channel    频道
     * @param connection 连接
     */
    public static void close(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
