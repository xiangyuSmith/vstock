package com.vstock.admin.controller;

import com.vstock.admin.service.AdminService;
import com.vstock.admin.service.RoleService;
import com.vstock.db.entity.Admin;
import com.vstock.db.entity.Menu;
import com.vstock.db.entity.Role;
import com.vstock.ext.base.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    @Autowired
    RoleService roleService;

    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "admin/index";
    }

    @RequestMapping("menuList")
    public String menuList(HttpServletRequest request, HttpSession session, ModelMap model) {
        List<Menu> resultMenu = new ArrayList<>();
        Admin admin = (Admin) session.getAttribute("admin");
        List<Menu> menuList = adminService.findMenuList();
        if(admin.getRoleId() != null){
            Role role = roleService.findById(admin.getRoleId());
            if(role.getRole_permissions() != null && !"".equals(role.getRole_permissions())){
                String[] permissionses = role.getRole_permissions().split(",");
                for (Menu m: menuList) {
                    for (String p : permissionses) {
                        if(p.equals(m.getId())){
                            resultMenu.add(m);
                        }
                    }
                }
            }
        }
        model.addAttribute("menuList", resultMenu);
        return "admin/common/menu";
    }
}
