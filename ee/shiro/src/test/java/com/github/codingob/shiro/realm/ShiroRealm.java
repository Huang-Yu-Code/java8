package com.github.codingob.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ShiroRealm extends AuthorizingRealm {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOGGER.info("授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // TODO 从数据库读取数据
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("vip1");
        simpleAuthorizationInfo.addRole("vip2");
        simpleAuthorizationInfo.addRole("vip3");
        simpleAuthorizationInfo.addStringPermission("buy:lv1");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        String dbUsername = "admin";
        LOGGER.info("登录的用户名: " + principal);
        // TODO 从数据库读取数据对比
        if (principal.equals(dbUsername)) {
            return new SimpleAuthenticationInfo(
                    principal,
                    "6d6c3c2378af65557533d604aaa89243",
                    ByteSource.Util.bytes("XXX"),
                    this.getName());
        }
        return null;
    }
}
