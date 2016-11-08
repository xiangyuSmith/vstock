package com.vstock.db.dao;

import com.vstock.db.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * Created by xiangyu on 2016/5/11.
 */

public interface IAdminDao {

    void addAdmin(Admin admin);

    Admin findAdmin(@Param("username") String username);

    void updateAdmin(Admin admin);

}
