package com.github.codingob.se.io.inputstream;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

/**
 * FileInputStream
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
class FileInputStreamDemo {
    public static void main(String[] args) {
        URL inputFile = FileInputStreamDemo.class.getClassLoader().getResource("fileinput.txt");
        assert inputFile != null;
        File file = new File(inputFile.getFile());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int len;
            while ((len = fileInputStream.read()) != -1) {
                System.out.print((char) len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
