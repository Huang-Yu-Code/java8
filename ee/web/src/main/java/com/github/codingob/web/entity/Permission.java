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
public class Permission {
    private Long id;
    private String name;
    private Timestamp createTime;
    private boolean delete;

    public Permission(String name){
        this.name = name;
    }
}
