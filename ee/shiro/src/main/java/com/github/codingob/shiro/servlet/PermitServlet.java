package com.github.codingob.shiro.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权验证
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@WebServlet(name = "Permit", value = {"/home/guest/*","/home/admin/*"})
public class PermitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/permit.jsp").forward(req, resp);
    }
}
