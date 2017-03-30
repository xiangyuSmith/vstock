package com.vstock.admin.controller;

import com.vstock.admin.service.AdminService;
import com.vstock.admin.service.RoleService;
import com.vstock.db.entity.Admin;
import com.vstock.db.entity.Permissions;
import com.vstock.db.entity.Role;
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

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    AdminService adminService;

    @RequestMapping
    public String index(HttpServletRequest request,ModelMap model) {
        String pageNow = request.getParameter("pageNow");
        int tocount = roleService.findRoleCount();
        Page page = new Page(tocount,pageNow);
        List<Role> roleList = roleService.findRoleAll(page);
        model.put("roleList",roleList);
        model.put("page",page);
        return "admin/system/role";
    }

    @RequestMapping("addRole")
    public String addRole(HttpServletRequest request){ return "admin/system/addRole"; }

    @RequestMapping("addAdmins")
    public String addAdmins(HttpServletRequest request){ return "admin/system/addAdmins"; }

    @RequestMapping("admins")
    public String admins(HttpServletRequest request,ModelMap model) {
        List<Admin> resultList = new ArrayList<>();
        String pageNow = request.getParameter("pageNow");
        int tocount = adminService.findAdminCount();
        Page page = new Page(tocount,pageNow);
        List<Role> roleList = roleService.findAll();
        List<Admin> adminList = adminService.findAdminAll(page);
        for (Admin admin : adminList) {
            if(admin.getRoleId() != null){
                Role role = roleService.findById(admin.getRoleId());
                admin.setRoleId(role.getName());
                resultList.add(admin);
            }else{
                admin.setRoleId("未分配");
                resultList.add(admin);
            }
        }
        model.put("adminList",resultList);
        model.put("roleList",roleList);
        model.put("page",page);
        return "admin/system/admins";
    }

    @RequestMapping("searchPermissions")
    public String searchPermissions(HttpServletRequest request,ModelMap model){
        String roleId = request.getParameter("roleId");
        List<Permissions> permissionList = roleService.findPermissions();
        Role role = roleService.findById(roleId);
        String permissionses = role.getRole_permissions();
        if(!"".equals(permissionses) && permissionses != null){
            String[] ps = permissionses.split(",");
            model.addAttribute("ps",ps);
        }
        model.addAttribute("permissionList",permissionList);
        model.addAttribute("roleId",roleId);
        model.addAttribute("role",role);
        return "admin/system/permissions";
    }

    @RequestMapping("setPermissions")
    @ResponseBody
    public Map<String, Object> setPermissions(HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        String role_permissionses = request.getParameter("permissionses");
        String roleId = request.getParameter("roleId");
        Role r = new Role();
        r.setId(roleId);
        r.setRole_permissions(role_permissionses);
        int i = roleService.update(r);
        params.put("data",i);
        return params;
    }

    @RequestMapping("distributionRole")
    @ResponseBody
    public Map<String, Object> distributionRole(HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        String adminId = request.getParameter("adminId");
        String roleId = request.getParameter("roleId");
        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setRoleId(roleId);
        int i = adminService.update(admin);
        params.put("data",i);
        return params;
    }

    @RequestMapping("insertRole")
    @ResponseBody
    public Map<String, Object> insertRole(HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        String roleName = request.getParameter("roleName");
        Role role = new Role();
        role.setName(roleName);
        int i = roleService.insert(role);
        params.put("data",i);
        return params;
    }
}
