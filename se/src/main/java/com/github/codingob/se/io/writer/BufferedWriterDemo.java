package com.github.codingob.se.io.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * BufferedWriter
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
class BufferedWriterDemo {
    public static void main(String[] args) {
        String file = "D:\\Demo\\java\\java\\se\\src\\main\\resources\\output.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Hello World");
            writer.newLine();
            writer.write("Hello World");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
