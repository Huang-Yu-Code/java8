package com.github.codingob.web.realm;

import com.github.codingob.web.entity.Account;
import com.github.codingob.web.service.AccountService;
import com.github.codingob.web.service.AuthService;
import com.github.codingob.web.service.PermissionService;
import com.github.codingob.web.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

/**
 * 自定义Realm
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class WebRealm extends AuthorizingRealm {

    public WebRealm() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(AuthService.HASH_ALGORITHM_NAME);
        credentialsMatcher.setHashIterations(AuthService.HASH_ITERATIONS);
        this.setCredentialsMatcher(credentialsMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = RoleService.getRoleNamesByAccountUsername(principal);
        Set<String> permits = PermissionService.getPermitNamesByAccountUsername(principal);
        System.out.println(roles);
        System.out.println(permits);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        simpleAuthorizationInfo.setStringPermissions(permits);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        // 从数据库对比数据
        Account account = AccountService.getAccountByUsername(principal);
        if (account == null) {
            throw new UnknownAccountException();
        }
        boolean lock = account.isLock();
        String password = account.getPassword();
        if (lock) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(principal, password, ByteSource.Util.bytes(AuthService.KEY), this.getName());
    }
}
