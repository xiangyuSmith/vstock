package com.vstock.front.controller;

import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.MD5Util;
import com.vstock.front.service.UserService;
import com.vstock.front.service.VstockConfigService;
import com.vstock.front.service.interfaces.IVstockConfigService;
import com.vstock.server.ihuyi.Sendsms;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiangyu on 2016/11/28.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping("prLogin")
    @ResponseBody
    public ResultModel prLogin(){
        ResultModel resultModel = new ResultModel();
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            resultModel.setRetMsg("用户名或密码不能为空");
            return resultModel;
        }
        User user = userService.findUser(mobile);
        if(user == null){
            resultModel.setRetMsg("用户名或密码错误");
            return resultModel;
        }
        if (user.getMobile().equals(mobile) && user.getPassword().equals(MD5Util.getMD5String(user.getSalt() + password + User.REG_MD5_CODE))) {
            User users = new User();
            users.setId(user.getId());
            users.setLast_login_ip(userService.ipadder(request));
            users.setLast_login_time(DateUtils.getCurrentTimeAsString());
            if (userService.update(users) > 0) {
                WebUtils.setSessionAttribute(request, User.SESSION_USER_ID, user.getId());
//            Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
                resultModel.setRetCode(ResultModel.RET_OK);
            }else {
                resultModel.setRetMsg("登录失败！请联系管理员");
            }
            return resultModel;
        }else{
            resultModel.setRetMsg("用户名或密码错误");
        }
        return resultModel;
    }

    /**
     * 支付宝回调
     * @return
     */
    @RequestMapping("alipayLogin")
    public String alipayLogin(){
        ResultModel resultModel = new ResultModel();
        String is_success = request.getParameter("is_success");
        if ("T".equals(is_success)) {
            resultModel = userService.alipayLogin(request);
        }else{
            resultModel.setRetMsg("用户名或密码错误");
        }
        return "redirect:/index";
    }

    @RequestMapping("sendSms")
    @ResponseBody
    public ResultModel sendSms() {
        ResultModel resultModel = new ResultModel();
        int mobile_code = (int)((Math.random()*9+1)*100000);
        String mobile = getParam("mobile");
        String key = VstockConfigService.getConfig(IVstockConfigService.SENDSMS_IHUYI_KEY);
        String account = VstockConfigService.getConfig(IVstockConfigService.SENDSMS_IHUYI_ACCOUNT);
        if(Sendsms.sendHuyi(mobile,account,key,mobile_code)){
            WebUtils.setSessionAttribute(request, User.SESSION_USER_SIGN_CODE, mobile_code);
            resultModel.setRetCode(resultModel.RET_OK);
        }else{
            resultModel.setRetMsg("服务器繁忙，请稍后再试");
            resultModel.setRetCode(0);
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

    @RequestMapping("alipay")
    public String alipay() {
        return "/common/alipay/login/alipayapi";
    }
}
