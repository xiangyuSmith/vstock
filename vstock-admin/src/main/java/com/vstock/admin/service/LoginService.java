package com.vstock.admin.service;

import com.vstock.db.dao.IAdminDao;
import com.vstock.db.entity.Admin;
import com.vstock.ext.util.CookieTool;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.DictKeys;
import com.vstock.ext.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by xiangyu on 2016/5/11.
 */
@Service
public class LoginService {

    @Autowired
    IAdminDao AdminDao;

    /**
     * 注册管理员
     *
     * @param name
     * @param password
     * @return
     */
    public Admin checkRegister(String name, String password) {
        String salt = MD5Util.getMD5String(String.valueOf(Math.random()));
        String finalPassword = MD5Util.getSha(password + (MD5Util.getSha(salt + DictKeys.reg_login_miyan)));
        Admin admin = new Admin();
        admin.setUsername(name);
        admin.setPassword(finalPassword);
        admin.setSalt(salt);
        AdminDao.addAdmin(admin);
        return admin;
    }

    /**
     * 管理员登陆校验
     *
     * @return
     * @Param username
     * @Param password
     */
    public int checkLogin(String username, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        //取用户
        Admin admin = null;
        admin = AdminDao.findAdmin(username);
        if (admin == null) {
            return DictKeys.login_info_0;// 用户不存在
        }
        //验证密码
        if (MD5Util.getSha(password + (MD5Util.getSha(admin.getSalt() + DictKeys.reg_login_miyan))).equals(admin.getPassword())) {
            //更新登录信息
            admin.setLastLogin(DateUtils.dateToString(new Date()));
            try {
                AdminDao.updateAdmin(admin);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            //存储cookie
            int loginMaxAge = 30 * 24 * 60 * 60;   //定义账户密码的生命周期，这里是一个月。单位为秒
            CookieTool.addCookie(response, "adminName", admin.getUsername(), loginMaxAge);
            //TODO 验证是否记住密码
            CookieTool.addCookie(response, "adminPwd", admin.getPassword(), loginMaxAge);
            //存储用户
            session.setAttribute("admin", admin);
            return DictKeys.login_info_3;
        }
        return DictKeys.login_info_4;
    }

}
