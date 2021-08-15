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
    /**
     * id
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private boolean gender;
    /**
     * 账户余额
     */
    private BigDecimal money;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 逻辑删除
     */
    private boolean isDelete;

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
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", money=" + money +
                ", createTime=" + createTime +
                ", delete=" + isDelete +
                '}';
    }
}
