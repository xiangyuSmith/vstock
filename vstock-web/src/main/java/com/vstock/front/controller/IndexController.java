package com.vstock.front.controller;

import com.vstock.ext.base.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    private static Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping
    public String index(){
        String sss = request.getParameter("sss");
        System.out.println(sss);
        return "/index/index.jsp";
    }

}
