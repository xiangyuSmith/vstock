package com.vstock.admin.base.service;

import com.vstock.db.entity.User;
import org.springframework.stereotype.Service;

/**
 * Created by sunson on 2015/11/21.
 */
@Service
public class UserService {

    User user;

    public User getUser() {
        return user;
    }
}
