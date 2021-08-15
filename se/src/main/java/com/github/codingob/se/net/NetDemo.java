package com.github.codingob.se.net;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;

/**
 * Net
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class NetDemo {
    public static void main(String[] args) {
        URL url = null;
        InetAddress inetAddress = null;
        try {
            url = new URL("https://www.baidu.com/");
            inetAddress = InetAddress.getByName(url.getHost());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inetAddress != null) {
            System.out.println(inetAddress.getHostAddress());
            System.out.println(inetAddress.getHostName());
        } else {
            System.out.println("无法访问网络地址: " + url);
        }
        download();
    }

    private static void download() {
        URL url;
        InputStream is = null;
        OutputStream os = null;
        byte[] bytes = new byte[1024];
        int len;
        try {
            url = new URL("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png");
            is = new BufferedInputStream(url.openStream());
            os = new BufferedOutputStream(new FileOutputStream("baidu.png"));
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
