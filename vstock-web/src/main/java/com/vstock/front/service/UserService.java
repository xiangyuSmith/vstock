package com.vstock.front.service;

import com.vstock.db.dao.IUserDao;
import com.vstock.db.entity.User;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.ConstUtil;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.MD5Util;
import com.vstock.server.alipay.util.AlipayNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    IUserDao userDao;

    public int insertUser(User user){
        return userDao.insertUser(user);
    }

    public User findById(String suid){
        User u = new User();
        u.setId(suid);
        return userDao.findUser(u);
    }

    public User findUser(String mobile){
        User u = new User();
        u.setMobile(mobile);
        return userDao.findUser(u);
    }

    /**
     * 修改方法
     * @param record
     * @return
     */
    public int update(User record){return userDao.update(record);}

    /**
     * 支付宝登录
     * @param request
     * @return
     */
    public ResultModel alipayLogin(HttpServletRequest request){
        ResultModel resultModel = new ResultModel();
        Map<String, String> param = new HashMap<String, String>();
        String is_success = request.getParameter("is_success");
        String notify_id = request.getParameter("notify_id");
        String token = request.getParameter("token");
        String real_name = request.getParameter("real_name");
        String email = request.getParameter("email");
        String user_id = request.getParameter("user_id");
        String sign = request.getParameter("sign");
        String sign_type = request.getParameter("sign_type");
        String target_url = request.getParameter("target_url");
        String global_buyer_email = request.getParameter("global_buyer_email");
        param.put("is_success", is_success);
        param.put("notify_id", notify_id);
        param.put("token", token);
        param.put("real_name", real_name);
        param.put("email", email);
        param.put("user_id", user_id);
        param.put("sign", sign);
        param.put("sign_type", sign_type);
        param.put("target_url", target_url);
        param.put("global_buyer_email", global_buyer_email);
        if (AlipayNotify.verify(param)) {
            User user = new User();
            user.setPassword(MD5Util.getMD5String(user_id + User.REG_MD5_CODE));
            int i = userDao.findCount(user);
            if (i < 1){
                user.setStatus(1);
                user.setCreate_time(DateUtils.getCurrentTimeAsString());
                user.setNick(real_name);
                if (!email.contains("@")){
                    user.setMobile(email);
                }
                resultModel.setRetCode(this.insertUser(user));
            }else {
                resultModel.setRetCode(ResultModel.RET_OK);
            }
        }else {
            resultModel.setRetMsg("登录失败！");
        }
        return resultModel;
    }

}
