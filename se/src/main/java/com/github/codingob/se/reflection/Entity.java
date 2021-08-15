package com.github.codingob.se.reflection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/** 实体类
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
class Entity implements Serializable {
    private Long id;
    private String name;
    private boolean gender;
    private BigDecimal money;
    private Timestamp createTime;

    public Entity() {
    }

    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Entity(Long id, String name, boolean gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", money=" + money +
                ", createTime=" + createTime +
                '}';
    }
}
