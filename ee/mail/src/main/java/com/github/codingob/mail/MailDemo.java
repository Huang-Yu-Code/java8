package com.github.codingob.mail;

import com.github.codingob.mail.util.MailUtils;

/**
 * Mail
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class MailDemo {
    public static void main(String[] args) {
        MailUtils.send("13713507941@163.com", "主题:测试", "正文:测试");
    }
}
