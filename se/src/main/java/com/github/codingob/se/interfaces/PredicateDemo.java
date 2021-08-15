package com.github.codingob.se.interfaces;

import java.util.function.Predicate;

/**
 * Predicate
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = String::isEmpty;
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("java"));
    }
}
