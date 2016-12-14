package com.vstock.front.support.interceptor;

import com.vstock.db.entity.User;
import com.vstock.ext.base.ResultModel;
import com.vstock.front.service.UserService;
import com.vstock.front.service.VstockConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sunson on 2016/1/14.
 */
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Autowired
    VstockConfigService vstockConfigService;

    @Value("${admin.rooturl}")
    String adminrooturl;

    List<String> unloginUrls = new ArrayList<>();
    List<String> unlimitUrls = new ArrayList<>();
    String uid;
    User user;
    ResultModel resultModel = new ResultModel();

    @PostConstruct
    public void initData() {
//        unloginUrls.add("/index");
//        unloginUrls.add("/sorts");
//        unloginUrls.add("/detail");
//        unloginUrls.add("/login");
//        unloginUrls.add("/login/logout");
        unloginUrls.add("/user");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI().substring(request.getContextPath().length());
        // 静态资源直接返回
        if (uri.startsWith("/assets/")) {
            return true;
        }
        String basePath = adminrooturl + request.getContextPath();
        // 退出的话直接处理掉
        if (uri.equals("/login/logout")) {
            WebUtils.setSessionAttribute(request, User.SESSION_USER_ID, null);
            resultModel.setRelogin(false);
            user = null;
        }
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        if (suid != null) {
            uid = String.valueOf(suid);
            user = userService.findById(uid);
        }else{
            resultModel.setRelogin(false);
            if(checkNotNeedLogin(uri)){
                response.sendRedirect(basePath + "/index");
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Map<String, String> configMaps = vstockConfigService.getConfigMap();
        if (modelAndView != null) {
            modelAndView.addObject("resultModel",resultModel);
            modelAndView.addObject("vUser", user);
            modelAndView.addObject("configMap", configMaps);
        }
    }

    //白名单判断
    boolean checkNotNeedLogin(String uri) {
        for (String s : unloginUrls) {
            if (uri.startsWith(s)) {
                return true;
            }
        }
        return false;
    }

    boolean checkNoLimit(String uri) {
        for (String s : unlimitUrls) {
            if (uri.startsWith(s)) {
                return true;
            }
        }
        return false;
    }
}
