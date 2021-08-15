package com.github.codingob.web.mapper;

import com.github.codingob.web.entity.Role;
import org.apache.ibatis.annotations.Param;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public interface RoleMapper {
    int create(Role role);

    Role getById(@Param("id") long id);

    Role getByName(@Param("name") String name);

    int updateById(Role role);

    int deleteById(@Param("id") long id);
}
