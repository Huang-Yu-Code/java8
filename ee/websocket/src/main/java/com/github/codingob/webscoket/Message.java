package com.github.codingob.webscoket;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消息
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Message {
    private String username;
    private String content;
    private final String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String message) {
        this.content = message;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
