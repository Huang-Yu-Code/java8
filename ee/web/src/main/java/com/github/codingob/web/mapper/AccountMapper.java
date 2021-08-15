package com.github.codingob.web.mapper;

import com.github.codingob.web.entity.Account;
import org.apache.ibatis.annotations.Param;

/**
 * 账号CRUD
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public interface AccountMapper {

    /**
     * 查询账号
     * @param username 账号
     * @return 账号数
     */
    int hasUsername(@Param("username") String username);

    /**
     * 创建账号
     *
     * @param account 账号
     * @return 插入条数
     */
    int create(Account account);

    /**
     * 根据Id获取账号信息
     *
     * @param id Id
     * @return Account
     */
    Account getById(@Param("id") long id);

    Account getByUsername(@Param("username") String username);

    /**
     * 根据Id更新账号
     *
     * @param account 账号
     * @return 跟新条数
     */
    int updatePasswordById(Account account);

    int updateLockById(Account account);

    /**
     * 根据Id逻辑删除账号
     *
     * @param id Id
     * @return 删除条数
     */
    int deleteById(@Param("id") long id);

}
