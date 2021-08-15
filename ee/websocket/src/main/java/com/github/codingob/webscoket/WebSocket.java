package com.github.codingob.webscoket;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * WebSocket
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@ServerEndpoint("/websocket/{username}")
public class WebSocket {
    private static final List<Message> MESSAGES = new CopyOnWriteArrayList<>();
    private static final Map<String, Session> CLIENT = new ConcurrentHashMap<>();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private String username;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws Exception {
        if (!CLIENT.containsKey(username)) {
            this.username = username;
            CLIENT.put(username, session);
            onOpen();
        } else {
            session.getBasicRemote().sendText(MAPPER.writeValueAsString(Notify.error("用户名已存在!")));
            session.close();
        }
    }

    private void onOpen() throws Exception {
        // 上线提示
        send(Notify.online(username));
        // 在线数据
        send(Data.users(CLIENT.keySet()));
        // 聊天数据
        send(Data.messages(MESSAGES));
    }

    /**
     * 关闭连接
     *
     * @throws Exception 异常
     */
    @OnClose
    public void onClose() throws Exception {
        if (username != null && CLIENT.containsKey(username)) {
            CLIENT.remove(username);
            send(Notify.offline(username));
            send(Data.users(CLIENT.keySet()));
        }
    }

    /**
     * 接收消息
     *
     * @param webSocketData 数据
     */
    @OnMessage
    public void onMessage(String webSocketData) throws Exception {
        Message message = MAPPER.readValue(webSocketData, Message.class);
        System.out.println(message);
        MESSAGES.add(message);
        send(Data.message(message));
    }

    /**
     * 连接错误
     *
     * @param throwable 异常
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 向所有客户端推送消息
     *
     * @param webSocketData 数据
     * @throws Exception 异常
     */
    private void send(WebSocketData webSocketData) throws Exception {
        for (Session session : CLIENT.values()) {
            session.getBasicRemote().sendText(MAPPER.writeValueAsString(webSocketData));
        }
    }
}
