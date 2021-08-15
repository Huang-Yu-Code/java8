package com.github.codingob.se.socket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Socket客户端
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SocketClient {

    private Socket socket;

    private void connection(String host, int port) {
        try {
            socket = new Socket(host, port);
            send();
            receive();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void send() throws Exception {
        OutputStream outputStream = socket.getOutputStream();
        System.out.println("发送信息: ");
        Scanner scanner = new Scanner(System.in);
        outputStream.write(scanner.nextLine().getBytes());
        outputStream.flush();
        socket.shutdownOutput();
    }

    private void receive() throws Exception {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = new ByteArrayOutputStream();
        int len;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        System.out.println(outputStream);
        outputStream.close();
        socket.shutdownInput();
    }

    public static void main(String[] args) {
        new SocketClient().connection("localhost", 8080);
    }

}
