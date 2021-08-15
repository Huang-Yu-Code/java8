package com.demo.mysql;

import com.demo.mysql.dao.EntityDao;
import com.demo.mysql.entity.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * EntityDao测试类
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class MySqlTest {

    private static final EntityDao entityDao = new EntityDao();

    @Test
    void create() {
        Entity entity = new Entity(1, "jdbc");
        int i = entityDao.create(entity);
        Assertions.assertEquals(1, i);
    }

    @Test
    void getList() {
        List<Entity> list = entityDao.getList();
        Assertions.assertNotNull(list);
    }

    @Test
    void getListByPage() {
        List<Entity> list = entityDao.getListByPage(2, 10);
        Assertions.assertNotNull(list);
    }

    @Test
    void getById() {
        Entity entity = entityDao.getById(1);
        Assertions.assertNotNull(entity);

    }

    @Test
    void updateById() {
        Entity entity = entityDao.getById(1);
        entity.setMoney(new BigDecimal(2000));
        int i = entityDao.updateById(entity);
        Assertions.assertEquals(1, i);
    }

    @Test
    void deleteById() {
        int i = entityDao.deleteById(1);
        Assertions.assertEquals(1, i);
    }

    @Test
    void delete() {
        int i = entityDao.deleteById(1, true);
        Assertions.assertEquals(1, i);
    }

    @Test
    void transfer() {
        if (entityDao.getById(1) != null) {
            entityDao.deleteById(1, true);
        }
        if (entityDao.getById(2) != null) {
            entityDao.deleteById(2, true);
        }
        Entity entity1 = new Entity();
        entity1.setId(1);
        entity1.setName("from");
        entityDao.create(entity1);

        Entity entity2 = new Entity();
        entity2.setId(2);
        entity2.setName("to");
        entityDao.create(entity2);

        boolean transfer = entityDao.transfer(1, 2, new BigDecimal(1000));

        Assertions.assertTrue(transfer);
    }

    @Test
    void sqlInjection() {
        if (entityDao.getById(1) == null) {
            Entity entity = new Entity();
            entity.setId(1);
            entity.setName("jdbc");
            entityDao.create(entity);
        }
        boolean sqlInjection = entityDao.sqlInjection(1, "name or 1=1");
        Assertions.assertTrue(sqlInjection);
    }
}
