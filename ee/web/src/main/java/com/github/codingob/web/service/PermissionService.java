package com.github.codingob.web.service;

import com.github.codingob.web.entity.Permission;
import com.github.codingob.web.entity.Role;
import com.github.codingob.web.entity.RolePermission;
import com.github.codingob.web.mapper.PermissionMapper;
import com.github.codingob.web.mapper.RolePermissionMapper;
import com.github.codingob.web.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限操作
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class PermissionService {
    public static Set<Permission> getPermitsByRoleId(long roleId) {
        Set<Permission> permissions = new HashSet<>();
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession()) {
            RolePermissionMapper rolePermissionMapper = sqlSession.getMapper(RolePermissionMapper.class);
            Set<RolePermission> rolePermissions = rolePermissionMapper.getByRoleId(roleId);
            for (RolePermission rolePermission : rolePermissions) {
                permissions.add(getPermitById(rolePermission.getPermissionId()));
            }
            return permissions;
        }
    }

    public static Set<Permission> getPermitsByRoleName(String name) {
        Role role = RoleService.getRoleByName(name);
        return getPermitsByRoleId(role.getId());
    }

    public static Set<String> getPermitNamesByRoleId(long roleId) {
        Set<String> permits = new HashSet<>();
        for (Permission permission : getPermitsByRoleId(roleId)) {
            permits.add(permission.getName());
        }
        return permits;
    }

    public static Set<String> getPermitNamesByRoleName(String name) {
        Role role = RoleService.getRoleByName(name);
        return getPermitNamesByRoleId(role.getId());
    }

    public static Permission getPermitById(long id) {
        Permission permission;
        try (SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession()) {
            final PermissionMapper permissionMapper = sqlSession.getMapper(PermissionMapper.class);
            permission = permissionMapper.getById(id);
        }
        return permission;
    }

    public static Set<String> getPermitNamesByAccountUsername(String username){
        Set<String> permits = new HashSet<>();
        Set<String> roles = RoleService.getRoleNamesByAccountUsername(username);
        for (String role: roles){
            permits.addAll(PermissionService.getPermitNamesByRoleName(role));
        }
        return permits;
    }
}
