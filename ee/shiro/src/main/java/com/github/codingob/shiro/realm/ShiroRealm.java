package com.github.codingob.shiro.realm;

import com.github.codingob.shiro.util.AccountUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义Realm
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ShiroRealm extends AuthorizingRealm {

    public ShiroRealm() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(AccountUtils.HASH_ALGORITHM_NAME);
        credentialsMatcher.setHashIterations(AccountUtils.HASH_ITERATIONS);
        this.setCredentialsMatcher(credentialsMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        // TODO 从数据库获取数据
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(AccountUtils.ROLES.get(principal));
        System.out.println(AccountUtils.ROLES.get(principal));
        simpleAuthorizationInfo.setStringPermissions(AccountUtils.PERMITS.get(principal));
        System.out.println(AccountUtils.PERMITS.get(principal));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        // TODO 从数据库对比数据
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        if (AccountUtils.USERS.containsKey(principal)) {
            if (AccountUtils.USERS.get(principal).isLock()){
                throw new LockedAccountException();
            }
            else {
                simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                        principal,
                        AccountUtils.USERS.get(principal).getPassword(),
                        ByteSource.Util.bytes(AccountUtils.KEY),
                        this.getName());
            }
        }
        return simpleAuthenticationInfo;
    }
}
