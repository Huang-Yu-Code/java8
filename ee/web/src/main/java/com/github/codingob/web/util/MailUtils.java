package com.github.codingob.web.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * Mail工具类
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class MailUtils {
    private static String username;
    private static String password;

    static {
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = MailUtils.class.getClassLoader();
            properties.load(classLoader.getResourceAsStream("mail.properties"));
            username = properties.getProperty("mail.username");
            password = properties.getProperty("mail.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMail(String mail, String subject, String text) {
        Properties properties = new Properties();
        try {
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            message.setSubject(subject, "UTF-8");
            message.setText(text);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
