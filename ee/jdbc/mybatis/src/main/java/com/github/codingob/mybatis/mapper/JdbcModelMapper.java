package com.github.codingob.mybatis.mapper;

import com.github.codingob.mybatis.model.JdbcModel;

public interface JdbcModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdbcModel record);

    int insertSelective(JdbcModel record);

    JdbcModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdbcModel record);

    int updateByPrimaryKey(JdbcModel record);
}