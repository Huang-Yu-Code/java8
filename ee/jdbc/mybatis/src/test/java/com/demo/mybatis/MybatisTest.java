package com.demo.mybatis;

import com.demo.mybatis.entity.Entity;
import com.demo.mybatis.service.EntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * MybatisTest
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class MybatisTest {

    @Test
    void create() {
        Integer id = 1;
        Entity entity = new Entity(id, "mybatis-create");
        int i = EntityService.create(entity);
        System.out.println(entity);
        Assertions.assertEquals(1, i);
    }

    @Test
    void getById() {
        int id = 1;
        Entity entity = EntityService.getById(id);
        Assertions.assertNotNull(entity);
    }

    @Test
    void getByIdFromCache() {
        int id1 = 1;
        int id2 = 1;
        EntityService.getByIdFromCache(id1, id2);
    }

    @Test
    void getList() {
        List<Entity> list = EntityService.getList();
        Assertions.assertNotNull(list);
    }

    @Test
    void updateById() {
        int id = 1;
        BigDecimal money = new BigDecimal(2000);
        Entity entity = EntityService.getById(id);
        entity.setMoney(money);
        int i = EntityService.updateById(entity);
        Assertions.assertEquals(1, i);
    }

    @Test
    void deleteById() {
        int id = 1;
        int i = EntityService.deleteById(id);
        Assertions.assertEquals(1, i);
    }

    @Test
    void delete() {
        int id = 1;
        int i = EntityService.delete(id);
        Assertions.assertEquals(1, i);
    }

    @Test
    void transfer() {
        int id1 = 1;
        int id2 = 2;
        BigDecimal money = new BigDecimal(1000);
        if (EntityService.getById(id1) != null) {
            EntityService.delete(id1);
            EntityService.create(new Entity(id1, "mybatis-transfer-from"));
        }
        if (EntityService.getById(id2) != null) {
            EntityService.delete(id2);
            EntityService.create(new Entity(id2, "mybatis-transfer-to"));
        }
        EntityService.transfer(id1, id2, money);
    }

    @Test
    void getListById() {
        List<Entity> list = EntityService.getLisByPage();
        System.out.println(list);
        Assertions.assertEquals(10, list.size());
    }
}
