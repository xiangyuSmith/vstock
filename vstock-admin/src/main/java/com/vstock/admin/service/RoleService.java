package com.vstock.admin.service;

import com.vstock.db.dao.IRoleDao;
import com.vstock.db.entity.Permissions;
import com.vstock.db.entity.Role;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    IRoleDao roleDao;

    public int findRoleCount() {
        return roleDao.findRoleCount();
    }

    public Role findById(String roleId){ return roleDao.findById(roleId);}

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public List<Role> findRoleAll(Page page) {
        return roleDao.findRoleAll(page.getStartPos(), page.getPageSize());
    }

    public List<Permissions> findPermissions(){
        return roleDao.findPermissions();
    }

    public int update(Role role){ return roleDao.update(role); }

    public int insert(Role role){ return roleDao.insert(role); }
}
