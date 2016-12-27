package com.vstock.front.service;

import com.vstock.db.dao.IUserAccountDao;
import com.vstock.db.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    IUserAccountDao userAccountDao;

    public UserAccount findAccountByUid(String suid){
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(suid);
        List<UserAccount> userAccountList = findAll(userAccount);
        if(userAccountList.size() > 0){
            return userAccountList.get(0);
        }
        return null;
    }

    public List<UserAccount> findAll(UserAccount userAccount){
        return userAccountDao.findAll(userAccount);
    }

}
