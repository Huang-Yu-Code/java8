package com.github.codingob.web.servlet;

import com.github.codingob.web.util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传/下载
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@WebServlet(name = "File", value = {"/home/download", "/home/upload"})
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File("D:/file.txt");
        if (!file.exists()) {
            req.setAttribute("error", "资源不存在");
            doGet(req, resp);
        } else {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment;filename=file.txt");
            FileUtils.download(resp.getOutputStream(), file);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        try {
            FileUtils.upload(req);
            resp.getWriter().println();
        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().println(e.getMessage());
        }
    }
}
