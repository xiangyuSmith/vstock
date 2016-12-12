package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Trade;
import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.util.Page;
import com.vstock.front.service.BidService;
import com.vstock.front.service.TradeService;
import com.vstock.front.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by administor on 2016/12/6.
 */
@Controller
@RequestMapping("/bid")
public class BidController extends BaseController{
}
