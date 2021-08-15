package com.github.codingob.servlet.entity;

/**
 * 账号实体
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Account {
    private final String username;
    private final String password;
    private boolean lock;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lock=" + lock +
                '}';
    }
}
