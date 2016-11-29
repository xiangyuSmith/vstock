package com.vstock.front.controller;

import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.MD5Util;
import org.apache.commons.codec.digest.DigestUtils;
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

    @RequestMapping
    public String index() {
        return "/base/login";
    }

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
        // TODO 暂时固定死
        String salt = "dc3d1b16088e5d253a41f8d80bf06d067607059c";
        if ("15000000000".equals(mobile) && "eda8ace868382195f9c239eea33eb4f4".equals(MD5Util.getMD5String(salt + password + User.REG_MD5_CODE))) {
            WebUtils.setSessionAttribute(request, User.SESSION_USER_ID, 1);
            resultModel.setRetCode(ResultModel.RET_OK);
            return resultModel;
        }
        return resultModel;
    }
}
