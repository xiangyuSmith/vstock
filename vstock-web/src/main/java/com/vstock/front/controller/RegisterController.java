package com.vstock.front.controller;

import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.MD5Util;
import com.vstock.front.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController{

    @Autowired
    UserService userService;

    @RequestMapping
    public String index(){
        return "/base/register";
    }

    @RequestMapping("insertUser")
    @ResponseBody
    public ResultModel insertUser(){
        ResultModel resultModel = new ResultModel();
        String mobile = request.getParameter("mobile");
        String pwd = request.getParameter("password");
        String nowTime = request.getParameter("timestamp");
        if(StringUtils.isEmpty(pwd)){
            resultModel.setRetMsg("注册密码不能为空");
            return resultModel;
        }
        String salt = MD5Util.getSha(mobile + pwd + nowTime);
        User user = new User();
        user.setMobile(mobile);
        user.setPassword(MD5Util.getMD5String(salt + pwd + User.REG_MD5_CODE));
        user.setSalt(salt);
        userService.insertUser(user);
        return resultModel;
    }

}
