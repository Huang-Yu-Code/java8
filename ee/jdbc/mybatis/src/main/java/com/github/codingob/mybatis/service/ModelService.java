package com.github.codingob.mybatis.service;

import com.github.codingob.mybatis.mapper.JdbcModelMapper;
import com.github.codingob.mybatis.model.JdbcModel;
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
public class ModelService {
    /**
     * Mybatis会话工厂
     */
    private static SqlSessionFactory sqlSessionFactory;
    /**
     * Mapper映射接口
     */
    private JdbcModelMapper modelMapper;

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
    private JdbcModelMapper getMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(JdbcModelMapper.class);
    }

    /**
     * 插入数据
     *
     * @param jdbcModels 实体
     * @return 影响条数
     */
    public int addItems(List<JdbcModel> jdbcModels) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            modelMapper = getMapper(session);
            return modelMapper.insert(jdbcModels);
        }
    }

    /**
     * 更新数据
     *
     * @param jdbcModel 实体
     * @return 影响条数
     */
    public int updateById(JdbcModel jdbcModel) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            modelMapper = getMapper(session);
            return modelMapper.updateByPrimaryKey(jdbcModel);
        }
    }

    /**
     * 获取单条数据
     *
     * @return 影响条数
     */
    public JdbcModel getById(long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            modelMapper = getMapper(session);
            return modelMapper.selectByPrimaryKey(id);
        }
    }

    /**
     * 获取数据集合
     *
     * @return 影响条数
     */
    public List<JdbcModel> getList() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            modelMapper = getMapper(session);
            return modelMapper.selectAll();
        }
    }

    /**
     * 逻辑删除数据
     *
     * @param id ID
     * @return 影响条数
     */
    public int deleteById(long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            modelMapper = getMapper(session);
            return modelMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 物理删除数据
     *
     * @param id ID
     * @return 影响条数
     */
    public int delete(long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            modelMapper = getMapper(session);
            return modelMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 相同查询通过缓存返回
     */
    public void getByIdFromCache(long id1,long id2){
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            modelMapper = getMapper(session);
            System.out.println("========第一次查询===========");
            System.out.println(modelMapper.selectByPrimaryKey(id1));
            System.out.println("========第二次查询===========");
            System.out.println(modelMapper.selectByPrimaryKey(id2));
        }
    }

    /**
     * Mybatis事务
     *
     * @param id1   转出ID
     * @param id2   转入ID
     * @param money 转入金额
     */
    public void transfer(long id1, long id2, BigDecimal money) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            modelMapper = getMapper(session);
            JdbcModel jdbcModel1 = modelMapper.selectByPrimaryKey(id1);
            jdbcModel1.setMoney(jdbcModel1.getMoney().subtract(money));
            modelMapper.updateByPrimaryKey(jdbcModel1);

            JdbcModel jdbcModel2 = modelMapper.selectByPrimaryKey(id2);
            jdbcModel2.setMoney(jdbcModel2.getMoney().add(money));
            modelMapper.updateByPrimaryKey(jdbcModel2);
            if ((jdbcModel2.getMoney().compareTo(money.add(money))) == 0) {
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
    public List<JdbcModel> getLisByPage(int page, int size) {
        PageHelper.startPage(page, size);
        return getList();
    }

    /**
     * Mybatis分页,默认大小10条
     *
     * @param page 页
     * @return 分页后的数据列表
     */
    public List<JdbcModel> getLisByPage(int page) {
        final int size = 10;
        PageHelper.startPage(page, size);
        return getLisByPage(page, size);
    }

    /**
     * Mybatis分页，默认首页(1),大小10条
     *
     * @return 分页后的数据列表
     */
    public List<JdbcModel> getLisByPage() {
        final int page = 1;
        return getLisByPage(page);
    }
}
