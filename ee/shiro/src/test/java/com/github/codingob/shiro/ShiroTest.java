package com.github.codingob.shiro;

import com.github.codingob.shiro.realm.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ShiroTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShiroTest.class);

    public static void main(String[] args) {

        LOGGER.info("第一个Shiro应用！");
        // 创建SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        ShiroRealm realm = new ShiroRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        // 获取当前主题对象
        Subject subject = SecurityUtils.getSubject();

        // Session操作
        Session session = subject.getSession();
        session.setAttribute("key", "value");
        String key = "key";
        String value = "value";
        if (value.equals(session.getAttribute(key))) {
            LOGGER.info("获取到对应正确的值! [" + value + "]");
        }
        // 认证/登录
        LOGGER.info("认证状态: " + subject.isAuthenticated());
        if (!subject.isAuthenticated()) {
            // 创建Token令牌
            String username = "admin";
            String password = "password";
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            LOGGER.info("MD5加密前: " + password);
            Md5Hash md5Hash = new Md5Hash(password, "XXX", 1024);
            LOGGER.info("MD5加密后: " + md5Hash.toHex());
            token.setRememberMe(true);
            try {
                subject.login(token);
                LOGGER.info("用户 [" + subject.getPrincipal() + "] 登陆成功.");
                LOGGER.info("认证状态: " + subject.isAuthenticated());
            } catch (UnknownAccountException uae) {
                LOGGER.info("用户名不存在 " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                LOGGER.info("用户名或密码  " + token.getPrincipal() + " 错误!");
            } catch (LockedAccountException lae) {
                LOGGER.info("账号已被 " + token.getPrincipal() + " 禁止登录.  " + "请联系管理员解锁.");
            } catch (AuthenticationException ae) {
                LOGGER.info("其他自定义异常...");
            }
        }
        // 身份验证
        String role = "vip1";
        if (subject.hasRole(role)) {
            LOGGER.info("你拥有 " + role + " 这个身份!");
        } else {
            LOGGER.info("你没有这个身份! " + role);
        }
        if (subject.hasAllRoles(Arrays.asList("admin", "vip4"))) {
            LOGGER.info("你拥有 vip4 这个身份!");
        } else {
            LOGGER.info("你没有这个身份! vip4");
        }
        // 权限验证
        String permitted1 = "buy:lv1";
        if (subject.isPermitted(permitted1)) {
            LOGGER.info("你拥有 " + permitted1 + " 操作的权限.快去试试吧");
        } else {
            LOGGER.info("你没有这个权限! " + permitted1);
        }
        String permitted2 = "buy:lv2";
        if (subject.isPermitted(permitted2)) {
            LOGGER.info("你拥有 " + permitted2 + " 操作的权限.快去试试吧");
        } else {
            LOGGER.info("你没有这个权限! " + permitted2);
        }
        String permitted3 = "buy:lv3";
        if (subject.isPermitted(permitted3)) {
            LOGGER.info("你拥有 " + permitted3 + " 操作的权限.快去试试吧");
        } else {
            LOGGER.info("你没有这个权限! " + permitted3);
        }
        // 退出
        subject.logout();
        LOGGER.info("退出当前用户");
        // 结束
        System.exit(0);
    }
}
