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
    public String index(HttpServletRequest request,ModelMap model) {
        //这里编辑首页显示的数据
        int roleId = 1;
        //权限类型
        List<String> role = new ArrayList<String>();
        role.add("管理者");
        role.add("运营");
        role.add("财务");
        role.add("结算");
        model.addAttribute("roleid", roleId);
        model.addAttribute("rolename", role.get(roleId));
        //权限赋值
        switch (roleId)
        {
            case 0:
                int total_reg = 2300;//总注册数
                //todo: 得到总注册数
                int total_trade = 308;//总交易额
                //todo: 得到总交易额
                int total_bid = 80082;//总叫价数
                //todo: 得到总叫价数
                int total_online_user = 3000;//总在线用户
                //todo: 得到总在线用户
                model.addAttribute("total_reg", total_reg);
                model.addAttribute("total_trade", total_trade);
                model.addAttribute("total_bid", total_bid);
                model.addAttribute("total_online_user", total_online_user);
                int curmonth_reg = 1000;//当月注册数
                //todo: 得到当月注册数
                int curmonth_trade = 20000;//当月叫价数
                //todo: 得到当月叫价数
                int curmonth_bid = 300;//当月交易笔数
                //todo: 得到当月交易笔数
                int curmonth_online_user = 200000;//当月交易金额
                //todo: 得到当月交易金额
                model.addAttribute("curmonth_reg", curmonth_reg);
                model.addAttribute("curmonth_trade", curmonth_trade);
                model.addAttribute("curmonth_bid", curmonth_bid);
                model.addAttribute("curmonth_online_user", curmonth_online_user);
                break;
            case 1:
                int data_review = 14;//数据审查
                //todo: 数据审查
                int examine_review = 52;//验货待处理
                //todo: 验货待处理
                int send_review = 51;//发货待处理
                //todo: 发货待处理
                model.addAttribute("data_review", data_review);
                model.addAttribute("examine_review", examine_review);
                model.addAttribute("send_review", send_review);
                break;
            case 2:
                int total_refund = 4;//退款待审核
                //todo: 退款待审核
                int total_damages = 5;//赔偿待审核
                //todo: 赔偿待审核
                model.addAttribute("total_refund", total_refund);
                model.addAttribute("total_damages", total_damages);
                break;
            case 3:
                int today_money = 1;//今日结算
                //todo: 今日结算
                model.addAttribute("today_money", today_money);
            default:
        }
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
