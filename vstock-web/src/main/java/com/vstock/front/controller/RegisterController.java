package com.vstock.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiangyu on 2016/11/23.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping
    public String index(){
        return "";
    }

}
