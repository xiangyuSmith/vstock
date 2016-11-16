package com.vstock.admin.base.controller;

import com.vstock.admin.base.service.*;
import com.vstock.db.entity.Menu;
import com.vstock.ext.base.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by administor on 2016/5/9.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "admin/index";
    }

    @RequestMapping("menuList")
    public String menuList(HttpServletRequest request, ModelMap model) {
        List<Menu> menuList = adminService.findMenuList();
        model.addAttribute("menuList", menuList);
        return "admin/common/menu";
    }
}
