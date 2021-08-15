package com.github.codingob.se.interfaces;

import java.util.function.Function;

/**
 * Function
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class FunctionDemo {

    public static void main(String[] args) {
        Function<String, String> function = (str) -> "return "+str;

        System.out.println(function.apply("Function"));
    }
}
