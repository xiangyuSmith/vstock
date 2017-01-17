package com.vstock.admin.controller;

import com.vstock.admin.service.RefundService;
import com.vstock.db.entity.Refund;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.util.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by administor on 2016/5/9.
 */
@Controller
@RequestMapping("/admin")
public class RefundController extends BaseController {

    private static Logger logger = Logger.getLogger(RefundController.class);

    @Autowired
    RefundService refundService;

    @RequestMapping("index")
    public String index(Refund record, HttpServletRequest request, ModelMap model) {
        String pageNow = request.getParameter("pageNow");
        int totalCount =  refundService.findCount(record);
        Page page = new Page(totalCount,pageNow);
        List<Refund> refundList = refundService.findAll(record,page);
        model.put("refundList",refundList);
        return "admin/index";
    }

}
