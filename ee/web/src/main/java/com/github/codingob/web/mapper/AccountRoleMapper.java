package com.github.codingob.web.mapper;

import com.github.codingob.web.entity.AccountRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public interface AccountRoleMapper {

    int create(AccountRole accountRole);

    AccountRole getById(@Param("id") long id);

    Set<AccountRole> getByAccountId(@Param("accountId") long accountId);

    int updateById(AccountRole accountRole);

    int deleteById(@Param("id") long id);
}
