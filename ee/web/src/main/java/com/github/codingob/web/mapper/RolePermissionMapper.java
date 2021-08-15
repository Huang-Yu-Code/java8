package com.github.codingob.web.mapper;

import com.github.codingob.web.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public interface RolePermissionMapper {
    int create(RolePermission rolePermission);

    RolePermission getById(@Param("id") long id);

    Set<RolePermission> getByRoleId(@Param("roleId") long roleId);

    int updateById(RolePermission rolePermission);

    int deleteById(@Param("id") long id);
}
