package com.github.codingob.web.servlet;

import com.github.codingob.web.service.AuthService;
import com.github.codingob.web.util.CodeUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@WebServlet(name = "Code", value = "/code/*")
public class CodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("image/jpeg");
        String codeString = CodeUtils.createCodeString(4);
        req.getSession().setAttribute("code", codeString);
        ServletOutputStream outputStream = res.getOutputStream();
        outputStream.write(CodeUtils.createCodeImage(100, 30, codeString));
        outputStream.flush();
    }

}
