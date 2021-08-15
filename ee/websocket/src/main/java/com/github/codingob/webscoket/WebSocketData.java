package com.github.codingob.webscoket;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * WebSocketData
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */

public class WebSocketData {
    /**
     * 数据类型
     * 0系统通知
     * 1用户数据
     * 2聊天数据
     * 3消息数据
     */
    private int type;
    private Object data;
    private final String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

    public WebSocketData(Object data) {
        this.data = data;
    }

    public WebSocketData(int type, Object data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }
}
