package com.demo.mysql.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 实体类
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class Entity {

    private Integer id;
    private String name;
    private boolean gender;
    private BigDecimal money;
    private Timestamp createTime;
    private boolean delete;

    public Entity() {

    }

    public Entity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", money=" + money +
                ", createTime=" + createTime +
                ", delete=" + delete +
                '}';
    }
}
