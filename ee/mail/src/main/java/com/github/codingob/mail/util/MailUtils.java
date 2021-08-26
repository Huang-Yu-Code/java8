package com.github.codingob.mail.util;

import com.github.codingob.mail.MailDemo;

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
    private static final Properties PROPERTIES = new Properties();
    private static boolean debug;
    private static String username;
    private static String password;

    static {
        try {
            ClassLoader classLoader = MailDemo.class.getClassLoader();
            PROPERTIES.load(classLoader.getResourceAsStream("mail.properties"));
            debug = Boolean.parseBoolean(PROPERTIES.getProperty("mail.debug"));
            username = PROPERTIES.getProperty("mail.username");
            password = PROPERTIES.getProperty("mail.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Session getSession() {
        Session session = Session.getDefaultInstance(PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }

        });
        session.setDebug(debug);
        return session;
    }

    private static Message getMessage(String address, String subject, String text) throws MessagingException {
        MimeMessage message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
        message.setSubject(subject, "UTF-8");
        message.setText(text, "UTF-8");
        return message;
    }

    public static void send(String mail, String subject, String text) {
        try {
            Transport.send(getMessage(mail, subject, text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
