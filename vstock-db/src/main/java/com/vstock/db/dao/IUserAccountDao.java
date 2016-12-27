package com.vstock.db.dao;

import com.vstock.db.entity.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserAccountDao {

    //查询所有
    List<UserAccount> findAll(@Param("obj") UserAccount record);

    //按id查询
    UserAccount findAccountByUid(@Param("userId") String userId);

    //添加数据
    int insert(UserAccount record);

    //修改数据
    int update(@Param("status") int status, @Param("invalidDate") String invalidDate, @Param("id") Integer id);

}
