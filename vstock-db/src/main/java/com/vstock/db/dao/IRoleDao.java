package com.vstock.db.dao;

import com.vstock.db.entity.Permissions;
import com.vstock.db.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleDao {

    int findRoleCount();

    Role findById(@Param(value = "roleId") String roleId);

    List<Role> findAll();

    List<Role> findRoleAll(@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<Permissions> findPermissions();

    int update(@Param(value = "role") Role role);

    int insert(Role role);
}
