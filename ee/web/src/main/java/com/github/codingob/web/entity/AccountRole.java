package com.github.codingob.web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@Data
@NoArgsConstructor
public class AccountRole {
    private Long id;
    private Long accountId;
    private Long roleId;
    private Timestamp createTime;
    private boolean delete;

    public AccountRole(Long accountId, Long roleId) {
        this.accountId = accountId;
        this.roleId = roleId;
    }
}
