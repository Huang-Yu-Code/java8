package com.github.codingob.webscoket;

/**
 * 通知
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */

public class Notify {
    public static WebSocketData error(String error) {
        return new WebSocketData(error);
    }

    public static WebSocketData online(String username) {
        return new WebSocketData(username + " 已上线!");
    }

    public static WebSocketData offline(String username) {
        return new WebSocketData(username + " 已下线!");
    }
}
