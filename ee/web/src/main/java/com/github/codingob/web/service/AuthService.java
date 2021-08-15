package com.github.codingob.web.service;

import com.github.codingob.web.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册/登录/注销
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class AuthService {
    public final static String HASH_ALGORITHM_NAME = "md5";
    public final static String KEY = "XXX";
    public final static int HASH_ITERATIONS = 1024;
    public final static int PASSWORD_ERROR_NUM = 4;
    public final static Map<String, Long> ACCOUNT_LOCK = new HashMap<>();
    public static final Map<String,String> HTTP_SESSION = new ConcurrentHashMap<>();

    private static void checkCode(HttpServletRequest request) {
        String code = request.getParameter("code").toLowerCase();
        String sessionCode = (String) request.getSession().getAttribute("code");
        if (code.isEmpty()) {
            request.setAttribute("tip", "验证码不能为空");
            return;
        }
        if (!sessionCode.equals(code)) {
            request.setAttribute("tip", "验证码不正确");
        }

    }

    public static void logon(HttpServletRequest request) {
        checkCode(request);
        String tip = (String) request.getAttribute("tip");
        if (tip != null) {
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        if (username.isEmpty()) {
            request.setAttribute("tip", "账号不能为空");
            return;
        }
        if (AccountService.getAccountByUsername(username) != null) {
            request.setAttribute("tip", "账号已存在");
            return;
        }
        if (password.isEmpty()) {
            request.setAttribute("tip", "密码不能为空");
            return;
        }
        if (rePassword.isEmpty()) {
            request.setAttribute("tip", "重复密码不能为空");
            return;
        }
        if (!password.equals(rePassword)) {
            request.setAttribute("tip", "两次密码不一致");
            return;
        }
        Account account = new Account(username, new Md5Hash(password, KEY, HASH_ITERATIONS).toHex());
        AccountService.addAccount(account);
        request.getSession().removeAttribute("code");
    }

    public static void login(HttpServletRequest request) {
        checkCode(request);
        String tip = (String) request.getAttribute("tip");
        if (tip != null) {
            return;
        }
        Subject subject = SecurityUtils.getSubject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            loginSuccess(request);
        } catch (UnknownAccountException uae) {
            request.setAttribute("tip", "账号或密码错误");
        } catch (IncorrectCredentialsException ice) {
            ACCOUNT_LOCK.put(username, ACCOUNT_LOCK.get(username) + 1);
            request.setAttribute("tip", "账号或密码错误(" + ACCOUNT_LOCK.get(username) + "),5次错误将会锁定账号!");
            if (ACCOUNT_LOCK.get(username) > PASSWORD_ERROR_NUM) {
                Account account = AccountService.getAccountByUsername(username);
                account.setLock(true);
                AccountService.updateLock(account);
                request.setAttribute("tip", username + " 账号已被锁定!");
            }
        } catch (LockedAccountException lae) {
            request.setAttribute("tip", "账号状态异常");
        } catch (AuthenticationException ae) {
            request.setAttribute("tip", "未知错误");
        }
    }

    public static void logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        System.out.println(httpSession.getId());
        HTTP_SESSION.remove(httpSession.getId());
        SecurityUtils.getSubject().logout();
    }

    private static void loginSuccess(HttpServletRequest request) {
        String username = request.getParameter("username");
        String token = request.getSession().getId();
        ACCOUNT_LOCK.put(username, 0L);
        HTTP_SESSION.put(token,username);
        request.getSession().setAttribute("token", token);
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("role", "admin".equals(username) ? "admin" : "guest");
        request.getSession().removeAttribute("code");
    }
}
