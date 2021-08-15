package com.demo.mysql.dao;

import com.demo.mysql.entity.Entity;
import com.demo.mysql.utils.MysqlUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class EntityDao {
    /**
     * 执行的SQL语句
     */
    private String sql;
    /**
     * 更新数据的影响行数
     */
    private int rows;
    /**
     * 数据库连接
     */
    private Connection connection;
    /**
     * 预编译SQL语句，可以防止SQL注入问题
     */
    private PreparedStatement preparedStatement;
    /**
     * 查询数据的结果集
     */
    private ResultSet resultSet;

    /**
     * 插入数据
     *
     * @param entity 实体
     * @return 影响行数
     */
    public int create(Entity entity) {
        sql = "insert into entity(id,name, gender) values (?,?,?)";
        connection = MysqlUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (entity.getId() == null) {
                preparedStatement.setNull(1, Types.INTEGER);
            } else {
                preparedStatement.setInt(1, entity.getId());
            }
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setBoolean(3, entity.isGender());
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlUtil.close(preparedStatement, connection);
        }
        return rows;
    }

    /**
     * 查询数据集合
     *
     * @param page 起始索引
     * @param size 数据条数
     * @return 数据集合
     */
    private List<Entity> getList(int page, int size) {
        sql = "select * from entity where is_delete=0 limit ?, ?";
        List<Entity> list = new ArrayList<>();
        try {
            connection = MysqlUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, page);
            preparedStatement.setInt(2, size);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Entity entity = new Entity();
                entity.setId(resultSet.getInt("id"));
                entity.setName(resultSet.getString("name"));
                entity.setGender(resultSet.getBoolean("gender"));
                entity.setMoney(resultSet.getBigDecimal("money"));
                entity.setCreateTime(resultSet.getTimestamp("create_time"));
                entity.setDelete(resultSet.getBoolean("is_delete"));
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlUtil.close(resultSet, preparedStatement, connection);
        }
        return list;
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    public List<Entity> getList() {
        return getList(0, Integer.MAX_VALUE);
    }

    /**
     * 分页查询
     *
     * @param page 页
     * @param size 页大小
     * @return 部分数据
     */
    public List<Entity> getListByPage(int page, int size) {
        if (page < 1) {
            page = 1;
        }
        if (size < 1) {
            size = 10;
        }
        return getList((page - 1) * size, size);
    }

    /**
     * 根据Id查询单个数据
     *
     * @param id 实体Id
     * @return 实体
     */
    public Entity getById(int id) {
        sql = "select * from entity where id=? and is_delete=0;";
        Entity entity = null;
        try {
            connection = MysqlUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                entity = new Entity();
                entity.setId(resultSet.getInt("id"));
                entity.setName(resultSet.getString("name"));
                entity.setGender(resultSet.getBoolean("gender"));
                entity.setMoney(resultSet.getBigDecimal("money"));
                entity.setCreateTime(resultSet.getTimestamp("create_time"));
                entity.setDelete(resultSet.getBoolean("is_delete"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlUtil.close(resultSet, preparedStatement, connection);
        }
        return entity;
    }

    /**
     * 根据实体Id更新实体全部信息
     *
     * @param entity 实体
     * @return 影响行数
     */
    public int updateById(Entity entity) {
        sql = "update entity set name=?,gender=?,money=? where id=? and is_delete=0;";
        connection = MysqlUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBoolean(2, entity.isGender());
            preparedStatement.setBigDecimal(3, entity.getMoney());
            preparedStatement.setInt(4, entity.getId());
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlUtil.close(preparedStatement, connection);
        }
        return rows;
    }

    /**
     * 根据Id删除数据(逻辑删除)
     *
     * @param id 实体Id
     * @return 影响的行数
     */
    public int deleteById(int id) {
        return deleteById(id, false);
    }

    /**
     * 据Id删除数据(物理删除)
     *
     * @param id     实体Id
     * @param delete 是否物理删除
     * @return 影响的行数
     */
    public int deleteById(int id, boolean delete) {
        if (delete) {
            sql = "delete from entity where id=?;";
        } else {
            sql = "update entity set is_delete=1 where id=? and is_delete=0;";
        }
        connection = MysqlUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlUtil.close(preparedStatement, connection);
        }
        return rows;
    }

    /**
     * 事务-模拟转账,发生异常回滚
     *
     * @param id1   转出账户
     * @param id2   转入账户
     * @param money 金额
     */
    public boolean transfer(int id1, int id2, BigDecimal money) {
        boolean result = false;
        String sql1 = "update entity set money=? where id=? and is_delete=0";
        String sql2 = "update entity set money=? where id=? and is_delete=0";
        Connection connection = MysqlUtil.getConnection();
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            // 关闭自动提交
            connection.setAutoCommit(false);
            Entity entity1 = getById(id1);
            Entity entity2 = getById(id2);

            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setBigDecimal(1, entity1.getMoney().subtract(money));
            preparedStatement1.setInt(2, id1);
            preparedStatement1.executeUpdate();

            if (entity1.getMoney().compareTo(money) == 0) {
                throw new RuntimeException("转账异常");
            }

            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setBigDecimal(1, entity2.getMoney().add(money));
            preparedStatement2.setInt(2, id2);
            preparedStatement2.executeUpdate();
            // 手动提交
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                // 回滚操作
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            MysqlUtil.close(null, preparedStatement2, null);
            MysqlUtil.close(null, preparedStatement1, connection);
        }
        return result;
    }

    /**
     * 根据Id和Name查找,SQL注入问题
     *
     * @param id   实体Id
     * @param name 实体名称
     * @return 是否存在
     */
    public boolean sqlInjection(int id, String name) {
        boolean login = false;
        sql = "select * from entity where id=" + id + " and name=" + name + ";";
        connection = MysqlUtil.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            if (statement.executeQuery(sql).next()) {
                login = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            MysqlUtil.close(connection);
        }
        return login;
    }
}

