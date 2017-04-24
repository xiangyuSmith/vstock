package com.vstock.front.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.MD5Util;
import com.vstock.front.service.UserService;
import com.vstock.front.service.VstockConfigService;
import com.vstock.front.service.interfaces.IVstockConfigService;
import com.vstock.server.alipay.config.AlipayConfig;
import com.vstock.server.ihuyi.Sendsms;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
        //获取access_token及用户userId调用方法
        AlipayClient alipayClient =new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.ALIPAY_APP_ID_LOGIN, AlipayConfig.private_key,"json","GBK",AlipayConfig.alipay_app_public_key,AlipayConfig.sign_type);
        AlipaySystemOauthTokenRequest requeste = new AlipaySystemOauthTokenRequest();
        //获取auth_code授权码换取access_token授权令牌，以code换取令牌
        requeste.setCode(request.getParameter("auth_code"));
        requeste.setGrantType("authorization_code");
        //获取用户基本信息方法
        AlipayClient alipayClients = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.ALIPAY_APP_ID_LOGIN, AlipayConfig.private_key, "json", "GBK", AlipayConfig.alipay_app_public_key);
        AlipayUserInfoShareRequest requests = new AlipayUserInfoShareRequest();
        try {
            //获取授权令牌
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(requeste);
            //获取用户个人信息
            AlipayUserInfoShareResponse response  = alipayClients.execute(requests, oauthTokenResponse.getAccessToken());
            if (response.isSuccess()) {
                resultModel = userService.alipayLogin(request,response);
            } else {
                //获取用户信息失败，刷新令牌
                requeste.setRefreshToken(oauthTokenResponse.getRefreshToken());
                requeste.setGrantType("refresh_token");
                //获取新的令牌
                oauthTokenResponse = alipayClient.execute(requeste);
                //重新调用用户信息
                requests = new AlipayUserInfoShareRequest();
                response = alipayClients.execute(requests, oauthTokenResponse.getAccessToken());
                if (response.isSuccess()) {
                    resultModel = userService.alipayLogin(request,response);
                } else {
                    resultModel.setRetMsg("用户名或密码错误");
                }
            }
            System.out.println(response.getBody());
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
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
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
        if(Sendsms.sendHuyi(mobile,account,key,content)){
            WebUtils.setSessionAttribute(request, User.SESSION_USER_SIGN_CODE, mobile_code);
            WebUtils.setSessionAttribute(request, User.SESSION_USER_SIGN_MOBILE, mobile);
            resultModel.setRetCode(resultModel.RET_OK);
        }else{
            resultModel.setRetMsg("服务器繁忙，请稍后再试");
            resultModel.setRetCode(0);
        }
        return resultModel;
    }

    /**
     * 连续发送第二条使用
     * @return
     */
    @RequestMapping("sendSmsT")
    @ResponseBody
    public ResultModel sendSmsT() {
        ResultModel resultModel = new ResultModel();
        int mobile_code = (int)((Math.random()*9+1)*100000);
        String mobile = getParam("mobile");
        String key = VstockConfigService.getConfig(IVstockConfigService.SENDSMS_IHUYI_KEY);
        String account = VstockConfigService.getConfig(IVstockConfigService.SENDSMS_IHUYI_ACCOUNT);
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
        if(Sendsms.sendHuyi(mobile,account,key,content)){
            WebUtils.setSessionAttribute(request, User.SESSION_USER_SIGN_TCODE, mobile_code);
            WebUtils.setSessionAttribute(request, User.SESSION_USER_SIGN_TMOBILE, mobile);
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

    @RequestMapping("islogin")
    @ResponseBody
    public ResultModel islogin() {
        ResultModel resultModel = new ResultModel();
        String uid = (String) WebUtils.getSessionAttribute(request,User.SESSION_USER_ID);
        if("".equals(uid) || uid == null){
            resultModel.setRelogin(false);
        }else{
            resultModel.setRelogin(true);
        }
        return resultModel;
    }

    @RequestMapping("alipay")
    public String alipay(ModelMap model) {
        String url = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm";
        url = url + "?app_id=" + AlipayConfig.ALIPAY_APP_ID_LOGIN + "&scope=auth_user,auth_base,auth_zhima,auth_ecard&redirect_uri=" + AlipayConfig.ALIPAY_LOGIN_URL;
        model.addAttribute("url",url);
        return "/common/alipay/login/login";
    }
}
