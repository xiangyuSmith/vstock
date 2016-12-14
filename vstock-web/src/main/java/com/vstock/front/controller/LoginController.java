package com.vstock.front.controller;

import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.MD5Util;
import com.vstock.front.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

/**
 * Created by xiangyu on 2016/11/28.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping("prLogin")
    @ResponseBody
    public ResultModel prLogin(){
        ResultModel resultModel = new ResultModel();
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            resultModel.setRetMsg("登录邮箱或者密码不能为空");
            return resultModel;
        }
        User user = userService.findUser(mobile);
        if(user == null){
            resultModel.setRetMsg("用户不存在");
            return resultModel;
        }
        if (user.getMobile().equals(mobile) && user.getPassword().equals(MD5Util.getMD5String(user.getSalt() + password + User.REG_MD5_CODE))) {
            WebUtils.setSessionAttribute(request, User.SESSION_USER_ID, user.getId());
            resultModel.setRetCode(ResultModel.RET_OK);
            return resultModel;
        }
        return resultModel;
    }

    @RequestMapping("logout")
    @ResponseBody
    public ResultModel logout() {
        ResultModel resultModel = new ResultModel();
        resultModel.setRelogin(false);
        return resultModel;
    }
}
