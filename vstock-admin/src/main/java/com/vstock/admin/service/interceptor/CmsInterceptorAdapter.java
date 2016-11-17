package com.vstock.admin.service.interceptor;

import com.vstock.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunson on 2015/11/21.
 */
@Service
public class CmsInterceptorAdapter extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    private List<String> ignoreList;

    @PostConstruct
    public void init() {
        ignoreList = new ArrayList<>();
        ignoreList.add("/admin/prelogin.do");
        ignoreList.add("/admin/login.do");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI().substring(request.getContextPath().length());
        // 退出的话直接处理掉
        if (uri.equals("/admin/logout.do")) {
            WebUtils.setSessionAttribute(request, "D_USER", null);
            response.sendRedirect(request.getContextPath() + "/admin/prelogin.do");
            return false;
        }
        // 权限判断
        if (!ignoreList.contains(uri) && null == WebUtils.getSessionAttribute(request, "D_USER")) {
            response.sendRedirect(request.getContextPath() + "/admin/prelogin.do");
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("user", userService.getUser());
        }
    }
}
