package com.demo.mybatis.service;

import com.demo.mybatis.entity.Entity;
import com.demo.mybatis.mapper.EntityMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * 业务逻辑(CRUD)
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class EntityService {
    /**
     * Mybatis会话工厂
     */
    private static SqlSessionFactory sqlSessionFactory;
    /**
     * Mapper映射接口
     */
    private static EntityMapper mapper;

    // 静态初始化SQL会话工厂
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Mapper接口
     *
     * @param sqlSession sql会话
     * @return Mapper接口
     */
    private static EntityMapper getMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(EntityMapper.class);
    }

    /**
     * 插入数据
     *
     * @param entity 实体
     * @return 影响条数
     */
    public static int create(Entity entity) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            mapper = getMapper(session);
            return mapper.create(entity);
        }
    }

    /**
     * 更新数据
     *
     * @param entity 实体
     * @return 影响条数
     */
    public static int updateById(Entity entity) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            mapper = getMapper(session);
            return mapper.updateById(entity);
        }
    }

    /**
     * 获取单条数据
     *
     * @return 影响条数
     */
    public static Entity getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            mapper = getMapper(session);
            return mapper.getById(id);
        }
    }

    /**
     * 获取数据集合
     *
     * @return 影响条数
     */
    public static List<Entity> getList() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            mapper = getMapper(session);
            return mapper.getList();
        }
    }

    /**
     * 逻辑删除数据
     *
     * @param id ID
     * @return 影响条数
     */
    public static int deleteById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            mapper = getMapper(session);
            return mapper.deleteById(id);
        }
    }

    /**
     * 物理删除数据
     *
     * @param id ID
     * @return 影响条数
     */
    public static int delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            mapper = getMapper(session);
            return mapper.delete(id);
        }
    }

    /**
     * 相同查询通过缓存返回
     */
    public static void getByIdFromCache(int id1,int id2){
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            mapper = getMapper(session);
            System.out.println("========第一次查询===========");
            System.out.println(mapper.getById(id1));
            System.out.println("========第二次查询===========");
            System.out.println(mapper.getById(id2));
        }
    }

    /**
     * Mybatis事务
     *
     * @param id1   转出ID
     * @param id2   转入ID
     * @param money 转入金额
     */
    public static void transfer(int id1, int id2, BigDecimal money) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            mapper = getMapper(session);
            Entity entity1 = mapper.getById(id1);
            entity1.setMoney(entity1.getMoney().subtract(money));
            mapper.updateById(entity1);

            Entity entity2 = mapper.getById(id2);
            entity2.setMoney(entity2.getMoney().add(money));
            mapper.updateById(entity2);
            if ((entity2.getMoney().compareTo(money.add(money))) == 0) {
                throw new RuntimeException("专账异常");
            }
            session.commit();
        }
    }

    /**
     * Mybatis分页
     *
     * @param page 页
     * @param size 页大小
     * @return 分页后的数据列表
     */
    public static List<Entity> getLisByPage(int page, int size) {
        PageHelper.startPage(page, size);
        return getList();
    }

    /**
     * Mybatis分页,默认大小10条
     *
     * @param page 页
     * @return 分页后的数据列表
     */
    public static List<Entity> getLisByPage(int page) {
        final int size = 10;
        PageHelper.startPage(page, size);
        return getLisByPage(page, size);
    }

    /**
     * Mybatis分页，默认首页(1),大小10条
     *
     * @return 分页后的数据列表
     */
    public static List<Entity> getLisByPage() {
        final int page = 1;
        return getLisByPage(page);
    }
}
