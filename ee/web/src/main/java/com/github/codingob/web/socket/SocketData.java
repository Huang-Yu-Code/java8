package com.github.codingob.web.socket;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@Data
public class SocketData {
    private String username;
    private String content;
    private Timestamp timestamp;
}
