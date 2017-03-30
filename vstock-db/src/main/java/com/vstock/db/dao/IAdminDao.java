package com.vstock.db.dao;

import com.vstock.db.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangyu on 2016/5/11.
 */

public interface IAdminDao {

    int findAdminCount();

    Admin findById(@Param(value = "adminId") String adminId);

    List<Admin> findAdminAll(@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    void addAdmin(Admin admin);

    Admin findAdmin(@Param("username") String username);

    int updateAdmin(Admin admin);

}
