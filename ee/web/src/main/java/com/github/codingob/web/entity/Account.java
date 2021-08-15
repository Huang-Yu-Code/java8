package com.github.codingob.web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.sql.Timestamp;

/**
 * 账号实体
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@Data
@NoArgsConstructor
public class Account {
    private Long id;
    private String username;
    private String password;
    private boolean lock;
    private Timestamp createTime;
    private boolean delete;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
