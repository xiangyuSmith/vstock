package com.vstock.admin.user.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiangyu on 2016/5/4.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping
    public String index(HttpServletRequest request) {
        System.out.println(request.getParameter("s"));
        return "base/index";
    }
}
