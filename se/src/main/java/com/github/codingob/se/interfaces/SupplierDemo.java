package com.github.codingob.se.interfaces;

import java.util.function.Supplier;

/**
 * Supplier
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Supplier";
        System.out.println(supplier.get());
    }
}
