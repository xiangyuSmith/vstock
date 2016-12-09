package com.vstock.front.support.interceptor;

import com.vstock.db.entity.User;
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

    @PostConstruct
    public void initData() {
        unloginUrls.add("/login");
        unloginUrls.add("/login/logout");
        unlimitUrls.add("/index");
        unlimitUrls.add("/sorts");
        unlimitUrls.add("/detail");
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
            response.sendRedirect(basePath + "/login");
            return false;
        }
        // 白名单判断
        if (checkNotNeedLogin(uri)) {
            return true;
        } else {
            Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
            if (suid == null) {
                response.sendRedirect(basePath + "/login");
                return false;
            }
            uid = String.valueOf(suid);
            user = userService.findById(uid);
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Map<String, String> configMaps = vstockConfigService.getConfigMap();
        if (modelAndView != null) {
            modelAndView.addObject("vUser", user);
            modelAndView.addObject("configMap", configMaps);
        }
    }

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
