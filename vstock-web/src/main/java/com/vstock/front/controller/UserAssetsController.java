package com.vstock.front.controller;

import com.vstock.ext.base.BaseController;
import com.vstock.front.service.UserAssetsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserAssetsController extends BaseController {
    @Autowired
    UserAssetsService userAssetsService;

    private static Logger logger = Logger.getLogger(BidController.class);
}
