package com.github.codingob.web.servlet;


import com.github.codingob.web.util.MailUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 发送邮箱
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@WebServlet(name = "Mail", value = "/home/mail")
public class MailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        MailUtils.sendMail(mail, subject, content);
        req.setAttribute("tip", "发送成功");
        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }
}
