package com.github.codingob.webscoket;

import java.util.Collection;

/**
 * 数据
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Data {
    public static WebSocketData users(Collection<String> userList) {
        return new WebSocketData(1, userList);
    }

    public static WebSocketData messages(Collection<Message> messages) {
        return new WebSocketData(2, messages);
    }

    public static WebSocketData message(Message message) {
        return new WebSocketData(3, message);
    }
}
