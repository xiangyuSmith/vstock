package com.vstock.front.controller;

import com.vstock.ext.base.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sorts")
public class SortsController extends BaseController{

    private static Logger logger = Logger.getLogger(SortsController.class);

    @RequestMapping
    public String index(){
        return "/sorts/index";
    }

    @RequestMapping("list")
    public String list(){
        return "/sorts/list";
    }
}
