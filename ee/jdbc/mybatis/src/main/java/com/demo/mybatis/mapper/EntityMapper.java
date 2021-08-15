package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.Entity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper接口
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public interface EntityMapper {

    /**
     * 插入数据
     *
     * @param entity 实体
     * @return 插入成功条数
     */
    int create(Entity entity);

    /**
     * 查询单条数据
     *
     * @param id 实体ID
     * @return 一条数据
     */
    Entity getById(@Param("id") int id);

    /**
     * 查询数据列表
     *
     * @return 数据集合
     */
    List<Entity> getList();

    /**
     * 更新数据
     *
     * @param entity 实体
     * @return 更新条数
     */
    int updateById(Entity entity);

    /**
     * 逻辑删除数据
     *
     * @param id Id
     * @return 逻辑删除条数
     */
    int deleteById(@Param("id") int id);

    /**
     * 物理删除数据
     *
     * @param id Id
     * @return 删除条数
     */
    int delete(@Param("id") int id);
}
