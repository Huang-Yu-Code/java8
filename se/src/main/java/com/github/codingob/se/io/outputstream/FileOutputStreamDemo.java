package com.github.codingob.se.io.outputstream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * FileOutputStream
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
class FileOutputStreamDemo {
    public static void main(String[] args) {
        URL outputFile = FileOutputStreamDemo.class.getClassLoader().getResource("fileoutput.txt");
        System.out.println(outputFile);
        assert outputFile != null;
        File file = new File(outputFile.getFile());
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            String txt = "File中文";
            fileOutputStream.write(txt.getBytes());
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
