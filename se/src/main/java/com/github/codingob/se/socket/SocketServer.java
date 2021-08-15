package com.github.codingob.se.socket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket服务器
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SocketServer {

    private static ServerSocket serverSocket;

    private volatile static boolean status = true;

    public static void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Socket服务启动成功!");
            while (status) {
                listener();
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
    }

    private static void listener() throws Exception {
        Socket socket = serverSocket.accept();
        System.out.println(socket.getInetAddress());
        receive(socket);
        ack(socket);
        socket.close();
    }

    private static void receive(Socket socket) throws Exception {
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

    private static void ack(Socket socket) throws Exception {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(("服务端接收成功").getBytes());
        socket.shutdownOutput();
    }

    public static void main(String[] args) {
        SocketServer.start(8080);
    }

}
