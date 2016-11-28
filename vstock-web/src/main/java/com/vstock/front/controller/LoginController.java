package com.vstock.front.controller;

import com.vstock.ext.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiangyu on 2016/11/28.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @RequestMapping
    public String index() {
        return "/base/login";
    }
}
