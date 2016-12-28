package com.vstock.admin.controller;

import com.vstock.admin.service.UserService;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.User;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangyu on 2016/5/4.
 */
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
