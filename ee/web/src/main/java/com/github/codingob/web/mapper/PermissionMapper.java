package com.github.codingob.web.mapper;

import com.github.codingob.web.entity.Permission;
import org.apache.ibatis.annotations.Param;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public interface PermissionMapper {
    int create(Permission permission);

    Permission getById(@Param("id") long id);

    int updateById(Permission permission);

    int deleteById(@Param("id") long id);
}
