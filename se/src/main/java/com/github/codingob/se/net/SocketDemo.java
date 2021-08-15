package com.github.codingob.se.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Socket
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SocketDemo {
    public static void main(String[] args) {

    }
}

class Server {
    private static ThreadPoolExecutor threadPool;
    private static ServerSocket server;
    private volatile static boolean status;
    private static AtomicInteger atomicInteger;

    static {
        try {
            server = new ServerSocket(8084);
            status = true;
            atomicInteger = new AtomicInteger();
            threadPool = new ThreadPoolExecutor(
                    2,
                    Runtime.getRuntime().availableProcessors(),
                    3,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(3),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while (status) {
            try {
                Socket socket = server.accept();
                String host = socket.getInetAddress().getHostAddress();
                atomicInteger.incrementAndGet();
                threadPool.execute(() -> {
                    try {
                        System.out.println("Socket客户端" + host + "连接,当前连接数: " + atomicInteger);
                        // 接收客户端数据
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        String data = inputStream.readUTF();
                        System.out.println("接收到客户端数据: " + data);
                        socket.shutdownInput();
                        // 向客户端发送数据
                        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                        outputStream.writeUTF("I'm Socket Server");
                        outputStream.flush();
                        socket.shutdownOutput();
                        // 关闭连接
                        socket.close();
                        atomicInteger.decrementAndGet();
                        System.out.println("Socket客户端" + host + "断开连接,当前连接数: " + atomicInteger);
                    } catch (Exception e) {
                        e.printStackTrace();
                        status = false;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
            }
        }
    }
}

class Client {
    private static Socket socket;

    static {
        try {
            socket = new Socket("localhost", 8084);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // 向服务端发送数据
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF("I'm Socket Client");
            outputStream.flush();
            socket.shutdownOutput();
            // 接收服务端数据
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String data = inputStream.readUTF();
            System.out.println("客户端接收数据: " + data);
            socket.shutdownInput();
            // 关闭连接
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
