package com.vstock.admin.controller;

import com.vstock.admin.service.UserService;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.User;
import com.vstock.db.entity.UserActivity;
import com.vstock.ext.util.Page;
import com.vstock.server.ihuyi.Sendsms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping
    public String index(HttpServletRequest request) {
        System.out.println(request.getParameter("s"));
        return "base/index";
    }

    @RequestMapping("userActivity")
    public String userActivity( HttpServletRequest request, ModelMap model) {
        String pageNow = request.getParameter("pageNow");
        int tocount = userService.findActivityCount();
        Page page = new Page(tocount,pageNow);
        List<UserActivity> userActivityList = userService.findActivityAll(page);
        model.put("userActivityList",userActivityList);
        model.put("page",page);
        return "admin/user/indexActivity";
    }

    @RequestMapping("sendUserActivityInfo")
    @ResponseBody
    public Map<String,Object> sendUserActivityInfo(HttpServletRequest request) {
        Map<String,Object> param = new HashMap<String,Object>();
        String mobile = request.getParameter("mobile");
        String key = "1ef20e77cb3e8c22412ecabced1b995c";
        String account = "cf_younme";
        String content = new String("尊敬的用户您好，恭喜您已经入选我们平台“100名VIP邀请活动”，为方便我们后续为您发放邀请码以及活动的进行，请及时关注我们的微信：vstock113");
        if(Sendsms.sendHuyi(mobile,account,key,content)){
            param.put("reGode","1");
        }else{
            param.put("reMessage","服务器繁忙，请稍后再试");
            param.put("reGode","0");
        }

        return param;
    }

    @RequestMapping("userindex")
    public String userindex(User record, HttpServletRequest request, ModelMap model) {
        List<String> sizeList = new ArrayList<String>();
        String pageNow = request.getParameter("pageNow");
        String linkAddress = request.getRequestURI() + "?1=1";
        linkAddress = userService.linkAddress(linkAddress,record);
        int tocount = userService.findCount(record);
        Page page = new Page(tocount,pageNow);
        List<User> userList = userService.findAll(record,page);
        for (int i = 0; i < Basicinformation.sizes.length; i++){
            sizeList.add(Basicinformation.sizes[i]);
        }
        model.put("sizeList",sizeList);
        model.put("userList",userList);
        model.put("record",record);
        model.put("page",page);
        model.put("linkAddress",linkAddress);
        return "admin/user/index";
    }

    @RequestMapping("saveUser")
    @ResponseBody
    public Map<String,Object> saveUser(HttpServletRequest request) {
        Map<String,Object> param = new HashMap<String,Object>();
        User record = new User();
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        record.setId(id);
        record.setStatus(Integer.parseInt(status));
        int reGode = userService.save(record);
        param.put("reGode",reGode);
        return param;
    }
}
