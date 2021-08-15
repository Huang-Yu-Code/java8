package com.github.codingob.shiro.util;

import com.github.codingob.shiro.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    public final static String ATTRIBUTE_ROLE = "role";
    private static final Logger logger = LoggerFactory.getLogger(AccountUtils.class);
    public final static String HASH_ALGORITHM_NAME = "md5";
    public final static String KEY = "XXX";
    public final static int HASH_ITERATIONS = 1024;
    public final static Map<String, Account> USERS = new HashMap<>();
    public final static int PASSWORD_ERROR_NUM = 4;
    public final static Map<String, Integer> ACCOUNT_LOCK = new HashMap<>();
    public final static Map<String, Set<String>> ROLES = new HashMap<>();
    public final static Map<String, Set<String>> PERMITS = new HashMap<>();

    public static void logon(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean check = checkCode(request);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
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
            addRole(username);
            addPermit(username);
            request.getSession().removeAttribute(ATTRIBUTE_CODE);
            response.sendRedirect("login");
        } else {
            request.getRequestDispatcher("jsp/logon.jsp").forward(request, response);
        }
    }

    private static void addUser(String username, String password) {
        USERS.put(username, new Account(username, new Md5Hash(password, KEY, HASH_ITERATIONS).toHex()));
        ACCOUNT_LOCK.put(username, 0);
    }

    private static void addRole(String username) {
        String admin = "admin";
        Set<String> guestRole = new HashSet<>();
        guestRole.add("guest");
        if (username.equals(admin)) {
            Set<String> adminRole = new HashSet<>(guestRole);
            adminRole.add("admin");
            ROLES.put(username, adminRole);
        } else {
            ROLES.put(username, guestRole);
        }

    }

    private static void addPermit(String username) {
        String admin = "admin";

        Set<String> guestPermit = new HashSet<>();
        guestPermit.add("guest:create");
        guestPermit.add("guest:read");
        guestPermit.add("guest:update");
        guestPermit.add("guest:delete");

        Set<String> adminPermit = new HashSet<>(guestPermit);
        adminPermit.add("admin:create");
        adminPermit.add("admin:read");
        adminPermit.add("admin:update");
        adminPermit.add("admin:delete");
        if (username.equals(admin)) {
            PERMITS.put(username, adminPermit);
        } else {
            PERMITS.put(username, guestPermit);
        }
    }

    public static void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean check = checkCode(request);
        if (check) {
            check = shiroLogin(request);
        }
        if (check) {
            String username = request.getParameter("username");
            request.getSession().setAttribute(ATTRIBUTE_USERNAME, username);
            request.getSession().setAttribute(ATTRIBUTE_ROLE, "admin".equals(username) ? "admin" : "guest");
            request.getSession().removeAttribute("code");
            response.sendRedirect("home");

        } else {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }

    private static boolean checkCode(HttpServletRequest request) {
        boolean check = true;
        String sessionCode = (String) request.getSession().getAttribute("code");
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

    private static boolean shiroLogin(HttpServletRequest request) {
        boolean check = true;
        Subject subject = SecurityUtils.getSubject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            ACCOUNT_LOCK.put(username, 0);
        } catch (UnknownAccountException uae) {
            check = false;
            request.setAttribute("tip", "账号或密码错误");
            logger.info("账号错误");
        } catch (IncorrectCredentialsException ice) {
            check = false;
            ACCOUNT_LOCK.put(username, ACCOUNT_LOCK.get(username) + 1);
            request.setAttribute("tip", "账号或密码错误(" + ACCOUNT_LOCK.get(username) + "),5次错误将会锁定账号!");
            if (ACCOUNT_LOCK.get(username) > PASSWORD_ERROR_NUM) {
                USERS.get(username).setLock(true);
                request.setAttribute("tip", username + " 账号已被锁定!");
            }
            logger.info("密码错误");
        } catch (LockedAccountException lae) {
            check = false;
            request.setAttribute("tip", "账号状态异常");
            logger.info("账号状态异常");
        } catch (AuthenticationException ae) {
            check = false;
            request.setAttribute("tip", "未知错误");
            logger.info("未知错误");
        }
        return check;
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SecurityUtils.getSubject().logout();
        request.getSession().invalidate();
        response.sendRedirect("index");
    }
}
