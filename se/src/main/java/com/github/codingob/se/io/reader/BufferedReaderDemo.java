package com.github.codingob.se.io.reader;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * BufferedReader
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
class BufferedReaderDemo {
    public static void main(String[] args) {
        String file = "D:\\Demo\\java\\java\\se\\src\\main\\resources\\input.txt";
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = reader.readLine();
            }
            System.out.println(stringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
