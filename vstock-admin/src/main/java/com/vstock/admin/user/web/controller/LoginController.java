package com.vstock.admin.user.web.controller;

import com.vstock.admin.base.util.DictKeys;
import com.vstock.admin.user.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xiangyu on 2016/7/18.
 */
@Controller
@RequestMapping("/adminLogin")
public class LoginController {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    LoginService loginService;

    @RequestMapping("index")
    public String login(@RequestParam(value = "status", defaultValue = "") String status, HttpServletRequest request, ModelMap model, RedirectAttributes attr) {
        model.addAttribute("status", status);
        return "admin/adminLogin/admin_login";
    }

    /**
     * 后台管理员登录
     *
     * @param session
     * @param request
     * @param response
     * @param attr
     * @return
     */
    @RequestMapping("saveAdmin")
    public String saveAdmin(HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int result = loginService.checkLogin(username, password, session, request, response);
        if (result == DictKeys.login_info_3) {
            return "admin/index";
        }
        if (result == DictKeys.login_info_0) {
            attr.addAttribute("status", DictKeys.login_info_0);
        }
        if (result == DictKeys.login_info_1) {
            attr.addAttribute("status", DictKeys.login_info_1);
        }
        if (result == DictKeys.login_info_4) {
            attr.addAttribute("status", DictKeys.login_info_1);
        }
        return "redirect:index";
    }

    @RequestMapping("register")
    public String register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        loginService.checkRegister(username, password);
        return "base/index";
    }
}
