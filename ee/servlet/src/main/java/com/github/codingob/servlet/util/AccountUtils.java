package com.github.codingob.servlet.util;

import com.github.codingob.servlet.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户注册/登录验证类
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class AccountUtils {
    public final static String ATTRIBUTE_CODE = "code";
    public final static String ATTRIBUTE_USERNAME = "username";
    public final static Map<String, Account> USERS = new HashMap<>();
    public final static int PASSWORD_ERROR_NUM = 4;
    public final static Map<String, Integer> ACCOUNT_LOCK = new HashMap<>();

    public static void logon(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        boolean check = checkCode(request);

        if (check && username.isEmpty()) {
            request.setAttribute("tip", "账号不能为空");
            check = false;
        }
        if (check && USERS.containsKey(username)) {
            request.setAttribute("tip", "账号已存在");
            check = false;
        }
        if (check && password.isEmpty()) {
            request.setAttribute("tip", "密码不能为空");
            check = false;
        }
        if (check && rePassword.isEmpty()) {
            request.setAttribute("tip", "重复密码不能为空");
            check = false;
        }
        if (check && !password.equals(rePassword)) {
            request.setAttribute("tip", "两次密码不一致");
            check = false;
        }
        if (check) {
            addUser(username, password);
            request.getSession().removeAttribute(ATTRIBUTE_CODE);
            response.sendRedirect("login");
        } else {
            request.getRequestDispatcher("jsp/logon.jsp").forward(request, response);
        }
    }

    private static void addUser(String username, String password) {
        USERS.put(username, new Account(username, password));
        ACCOUNT_LOCK.put(username, 0);
    }

    private static boolean checkCode(HttpServletRequest request) {
        boolean check = true;
        String sessionCode = (String) request.getSession().getAttribute(ATTRIBUTE_CODE);
        String code = request.getParameter("code").toLowerCase();
        if (code.isEmpty()) {
            request.setAttribute("tip", "验证码不能为空");
            check = false;
        }
        if (check && !sessionCode.equals(code)) {
            request.setAttribute("tip", "验证码不正确");
            check = false;
        }
        return check;
    }

    public static void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean check = checkCode(request);
        if (check) {
            check = servletLogin(request);
        }
        if (check) {
            request.getSession().setAttribute(ATTRIBUTE_USERNAME, request.getParameter(ATTRIBUTE_USERNAME));
            request.getSession().removeAttribute(ATTRIBUTE_CODE);
            response.sendRedirect("home");
        } else {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }

    }

    private static boolean servletLogin(HttpServletRequest request) {
        boolean check = true;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.isEmpty()) {
            request.setAttribute("tip", "账号不能为空");
            check = false;
        }
        if (check && !AccountUtils.USERS.containsKey(username)) {
            request.setAttribute("tip", "账号或密码错误");
            check = false;
        }
        if (check && AccountUtils.USERS.get(username).isLock()) {
            request.setAttribute("tip", "账号状态异常");
            check = false;
        }
        if (check && password.isEmpty()) {
            request.setAttribute("tip", "密码不能为空");
            check = false;
        }
        if (check && !AccountUtils.USERS.get(username).getPassword().equals(password)) {
            ACCOUNT_LOCK.put(username, ACCOUNT_LOCK.get(username) + 1);
            request.setAttribute("tip", "账号或密码错误(" + ACCOUNT_LOCK.get(username) + "),5次错误将会锁定账号!");
            if (ACCOUNT_LOCK.get(username) > PASSWORD_ERROR_NUM) {
                USERS.get(username).setLock(true);
                request.setAttribute("tip", username + " 账号已被锁定!");
            }
            check = false;
        } else {
            ACCOUNT_LOCK.put(username, 0);
        }
        return check;
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("index");
    }
}
