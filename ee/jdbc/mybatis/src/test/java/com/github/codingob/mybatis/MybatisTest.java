package com.github.codingob.mybatis;

import com.github.codingob.mybatis.model.JdbcModel;
import com.github.codingob.mybatis.service.ModelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 测试类
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class MybatisTest {

    private final ModelService modelService = new ModelService();

    @Test
    void addItems() {
        JdbcModel jdbcModel = new JdbcModel();
        jdbcModel.setId(1L);
        jdbcModel.setName("mybatis");
        JdbcModel[] jdbcModels = {jdbcModel};
        int i = modelService.addItems(Arrays.asList(jdbcModels));
        System.out.println(jdbcModel);
        Assertions.assertEquals(1, i);
    }

    @Test
    void getById() {
        JdbcModel jdbcModel = modelService.getById(1);
        Assertions.assertNotNull(jdbcModel);
    }

    @Test
    void getByIdFromCache() {
        modelService.getByIdFromCache(1L, 1L);
    }

    @Test
    void getList() {
        List<JdbcModel> list = modelService.getList();
        Assertions.assertNotNull(list);
    }

    @Test
    void updateById() {
        int id = 1;
        BigDecimal money = new BigDecimal(2000);
        JdbcModel jdbcModel = modelService.getById(id);
        jdbcModel.setMoney(money);
        int i = modelService.updateById(jdbcModel);
        Assertions.assertEquals(1, i);
    }

    @Test
    void deleteById() {
        int i = modelService.deleteById(1L);
        Assertions.assertEquals(1, i);
    }

    @Test
    void delete() {
        int i = modelService.delete(1L);
        Assertions.assertEquals(1, i);
    }

    @Test
    void transfer() {
        long id1 = 1L;
        long id2 = 2L;
        BigDecimal money = new BigDecimal(1000);
        if (modelService.getById(id1) != null) {
            modelService.delete(id1);
            JdbcModel jdbcModel1 = new JdbcModel();
            jdbcModel1.setId(id1);
            jdbcModel1.setName("mybatis-transfer-from");
            JdbcModel[] jdbcModels = {jdbcModel1};
            modelService.addItems(Arrays.asList(jdbcModels));
        }
        if (modelService.getById(id2) != null) {
            modelService.delete(id2);
            JdbcModel jdbcModel2 = new JdbcModel();
            jdbcModel2.setId(id2);
            jdbcModel2.setName("mybatis-transfer-from");
            JdbcModel[] jdbcModels = {jdbcModel2};
            modelService.addItems(Arrays.asList(jdbcModels));
        }
        modelService.transfer(id1, id2, money);
    }

    @Test
    void getListByPage() {
        List<JdbcModel> list = modelService.getLisByPage();
        System.out.println(list);
        Assertions.assertEquals(10, list.size());
    }
}
