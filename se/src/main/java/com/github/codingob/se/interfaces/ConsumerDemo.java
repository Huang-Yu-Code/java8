package com.github.codingob.se.interfaces;

import java.util.function.Consumer;

/**
 * Consumer
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println;
        consumer.accept("Consumer");
    }
}
