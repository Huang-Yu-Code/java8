package com.github.codingob.web.socket;

import com.github.codingob.web.service.AuthService;
import com.github.codingob.web.util.JsonUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@ServerEndpoint("/websocket/{token}")
public class WebSocket {
    private static final Map<String,Session> SESSION_MAP = new ConcurrentHashMap<>();
    private String username;

    /**
     * @param session  WebSocket session
     * @param token 账号
     * @throws Exception 异常
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws Exception {
        if (AuthService.HTTP_SESSION.containsKey(token)) {
            this.username = AuthService.HTTP_SESSION.get(token);
            SESSION_MAP.put(username,session);
        } else {
            session.close();
        }
    }

    /**
     * 关闭连接
     *
     */
    @OnClose
    public void onClose() {
        if (username!=null){
            SESSION_MAP.remove(username);
        }
    }

    /**
     * 接收消息
     *
     * @param data 数据
     */
    @OnMessage
    public void onMessage(String data) throws Exception {
        send(JsonUtils.toObject(data,SocketData.class));
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
     * 向客户端发送消息
     *
     * @throws Exception 异常
     */
    private void send(SocketData socketData) throws Exception {
        for (Session session: SESSION_MAP.values()){
            session.getBasicRemote().sendText(JsonUtils.toJson(socketData));
        }
    }

}
