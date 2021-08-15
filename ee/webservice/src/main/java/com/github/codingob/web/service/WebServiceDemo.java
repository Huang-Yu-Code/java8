package com.github.codingob.web.service;

import com.github.codingob.web.service.myservice.MyService;
import com.github.codingob.web.service.myservice.MyServiceService;

/**
 * WebService
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class WebServiceDemo {
    public static void main(String[] args) {
        MyService myService = new MyServiceService().getMyServicePort();
        String data = myService.getData("hello");
        System.out.println(data);
    }
}
