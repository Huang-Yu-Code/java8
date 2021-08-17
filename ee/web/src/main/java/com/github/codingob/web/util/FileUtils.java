package com.github.codingob.web.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 上传/下载工具类
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class FileUtils {
    public static void upload(HttpServletRequest request,String path) throws Exception {
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> fileItems = servletFileUpload.parseRequest(new ServletRequestContext(request));
        for (FileItem fileItem : fileItems) {
            if (fileItem.isFormField()) {
                String name = fileItem.getFieldName();
                System.out.println(name);
            } else {
                String name = fileItem.getName();
                long itemSize = fileItem.getSize();
                if (itemSize > 1024 * 500) {
                    throw new Exception("文件太大(" + itemSize/1024 + "K)");
                } else {
                    fileItem.write(new File(path + name));
                }
            }
        }
    }

    public static void download(OutputStream outputStream, File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        outputStream.flush();
        inputStream.close();
    }
}
