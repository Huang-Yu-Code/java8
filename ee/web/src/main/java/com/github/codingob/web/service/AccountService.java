package com.github.codingob.web.service;

import com.github.codingob.web.entity.Account;
import com.github.codingob.web.entity.AccountRole;
import com.github.codingob.web.entity.Role;
import com.github.codingob.web.mapper.AccountMapper;
import com.github.codingob.web.mapper.AccountRoleMapper;
import com.github.codingob.web.mapper.RoleMapper;
import com.github.codingob.web.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * 账号操作
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class AccountService {

    public static void addAccount(Account account) {
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession()) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.create(account);
            AccountRoleMapper accountRoleMapper = sqlSession.getMapper(AccountRoleMapper.class);
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getByName("user");
            accountRoleMapper.create(new AccountRole(account.getId(), role.getId()));
            sqlSession.commit();
        }

    }

    public static void updateLock(Account account) {
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession()) {
            final AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.updateLockById(account);
            sqlSession.commit();
        }
    }

    public static Account getAccountById(long accountId) {
        Account account;
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession(true)) {
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            account = accountMapper.getById(accountId);
        }
        return account;
    }

    public static Account getAccountByUsername(String username) {
        Account account;
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession(true)) {
            account = sqlSession.getMapper(AccountMapper.class).getByUsername(username);
        }
        return account;
    }
}
