package com.vstock.spider.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiangyu on 2016/5/5.
 * WebMagic.io 爬虫数据
 */
@Controller
@RequestMapping("/run")
public class RunController {

    @RequestMapping
    public String index(HttpServletRequest request) {
        return "admin/storeBasicInformation";
    }

}
