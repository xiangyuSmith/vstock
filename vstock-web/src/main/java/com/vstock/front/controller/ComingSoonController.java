package com.vstock.front.controller;

import com.vstock.ext.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comingSoon")
public class ComingSoonController extends BaseController {


    @RequestMapping
    public String index() {
        return "/index/comingSoon";
    }

}
