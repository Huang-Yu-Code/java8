package com.github.codingob.web.service.service;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Service
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@WebService
public class MyService {
    public String getData(String key) {
        return "参数为: " + key;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/myservice/data", new MyService());
        System.out.println("发布成功!");
    }
}
