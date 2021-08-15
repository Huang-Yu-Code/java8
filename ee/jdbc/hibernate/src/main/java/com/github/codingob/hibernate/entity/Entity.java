package com.github.codingob.hibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@Data
@NoArgsConstructor
public class Entity {
    private Integer id;
    private String name;
    private boolean gender;
    private BigDecimal money = new BigDecimal(1000);
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());
    private boolean delete;

    public Entity(String name) {
        this.name = name;
    }

    public Entity(String name, boolean gender) {
        this.name = name;
        this.gender = gender;
    }

}
