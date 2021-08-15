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
        MailUtils.sendMail("228259646@qq.com", "主题:测试", "正文:测试");
    }
}
