package com.github.codingob.web.servlet;

import com.github.codingob.web.entity.Role;
import com.github.codingob.web.util.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Json响应
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@WebServlet(name = "Json", value = "/home/json")
public class JsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().println(JsonUtils.toJson(new Role("Json")));
    }
}
