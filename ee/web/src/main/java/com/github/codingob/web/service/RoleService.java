package com.github.codingob.web.service;

import com.github.codingob.web.entity.Account;
import com.github.codingob.web.entity.AccountRole;
import com.github.codingob.web.entity.Role;
import com.github.codingob.web.mapper.AccountRoleMapper;
import com.github.codingob.web.mapper.RoleMapper;
import com.github.codingob.web.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashSet;
import java.util.Set;

/**
 * 身份操作
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class RoleService {
    public static Role getRoleByName(String name) {
        Role role;
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession()) {
            role = sqlSession.getMapper(RoleMapper.class).getByName(name);
            sqlSession.commit();
        }
        return role;
    }

    public static Role getRoleById(long id) {
        Role role;
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession()) {
            role = sqlSession.getMapper(RoleMapper.class).getById(id);
            sqlSession.commit();
        }
        return role;
    }

    public static Set<Role> getRolesByAccountId(long accountId) {
        Set<Role> roles = new HashSet<>();
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession()) {
            AccountRoleMapper accountRoleMapper = sqlSession.getMapper(AccountRoleMapper.class);
            Set<AccountRole> accountRoles = accountRoleMapper.getByAccountId(accountId);
            for (AccountRole accountRole : accountRoles) {
                Role role = getRoleByRoleId(accountRole.getRoleId());
                roles.add(role);
            }
            sqlSession.commit();
            return roles;
        }
    }

    public static Set<Role> getRolesByAccountUsername(String username) {
        Account account = AccountService.getAccountByUsername(username);
        return getRolesByAccountId(account.getId());
    }

    public static Set<String> getRoleNamesByAccountId(long accountId) {
        Set<String> roles = new HashSet<>();
        for (Role role : getRolesByAccountId(accountId)) {
            roles.add(role.getName());
        }
        return roles;
    }

    public static Set<String> getRoleNamesByAccountUsername(String username) {
        Account account = AccountService.getAccountByUsername(username);
        return getRoleNamesByAccountId(account.getId());
    }

    public static Role getRoleByRoleId(long id) {
        Role role;
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            role = roleMapper.getById(id);
        }
        return role;
    }
}
