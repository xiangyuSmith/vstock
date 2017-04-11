package com.vstock.front.controller;

import com.vstock.db.entity.User;
import com.vstock.db.entity.UserInvitation;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.MD5Util;
import com.vstock.front.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

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
        String sendSmsCode = getParam("sendSmsCode","");
        if(!sendSmsCode.equals(String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_CODE)))){
            resultModel.setRetMsg("验证码错误");
            return resultModel;
        }
        //查看邀请码
//        UserInvitation userInvitation = userService.findInvitation(yaoqingCode);
//        if(userInvitation == null){
//            resultModel.setRetMsg("邀请码错误");
//            return resultModel;
//        }
//        if("1".equals(userInvitation.getStatus())){
//            resultModel.setRetMsg("该邀请码已被使用");
//            return resultModel;
//        }
        String mobile = request.getParameter("mobile");
        String pwd = request.getParameter("password");
        String nick = request.getParameter("nick");
        String size = request.getParameter("size");
        String nowTime = request.getParameter("timestamp");
        if(StringUtils.isEmpty(pwd)){
            resultModel.setRetMsg("注册密码不能为空");
            return resultModel;
        }
        User isuser = userService.findUser(mobile);
        if(isuser != null){
            resultModel.setRetMsg("手机号已存在");
            return resultModel;
        }
        String salt = MD5Util.getSha(mobile + pwd + nowTime);
        User user = new User();
        user.setMobile(mobile);
        user.setPassword(MD5Util.getMD5String(salt + pwd + User.REG_MD5_CODE));
        user.setSalt(salt);
        user.setNick(nick);
        user.setSize(size);
        int result = userService.insertUser(user);
        if(result == 1){
            resultModel.setRetMsg("注册成功，即将自动登录...");
            resultModel.setRetCode(ResultModel.RET_OK);
        }
        return resultModel;
    }
}
