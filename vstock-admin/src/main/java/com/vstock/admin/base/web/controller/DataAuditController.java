package com.vstock.admin.base.web.controller;

import com.vstock.admin.base.service.BasicinformationService;
import com.vstock.admin.base.service.CommodityDataService;
import com.vstock.admin.base.service.DictionariesService;
import com.vstock.admin.base.service.StockxStoreService;
import com.vstock.admin.base.util.DateUtils;
import com.vstock.admin.base.util.Page;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.CommodityData;
import com.vstock.db.entity.Dictionaries;
import com.vstock.db.entity.StockxStore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by xiangyu on 2016/7/18.
 */
@Controller
@RequestMapping("/dataAudit")
public class DataAuditController {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    DictionariesService dictionariesService;
    @Autowired
    CommodityDataService commodityDataService;
    @Autowired
    BasicinformationService basicinformationService;
    @Autowired
    StockxStoreService stockxStoreService;

    /**
     * 过滤/审核 数据
     */
    @RequestMapping("list")
    public String dataAudit(Dictionaries dictionaries, HttpServletRequest request, ModelMap model) {
        String artNo = "";
        String pageNow = request.getParameter("pageNow");
        String status = request.getParameter("status");
        String productName = request.getParameter("productName");
        String storeName = request.getParameter("storeName");
        String colorStatus = request.getParameter("colorStatus");
        if(status == null){
            status = "0";
        }
        String linkAddress = request.getRequestURI()+"?";
        if (status != null && !"".equals(status)) {
            linkAddress = linkAddress + "&status=" + status;
        }
        if (productName != null && !"".equals(productName)) {
            linkAddress = linkAddress + "&productName=" + productName;
        }
        if (storeName != null && !"".equals(storeName)) {
            linkAddress = linkAddress + "&storeName=" + storeName;
        }
        if (colorStatus != null && !"".equals(colorStatus)) {
            linkAddress = linkAddress + "&colorStatus=" + colorStatus;
        }
        List<Dictionaries> dCListCount = dictionariesService.getCount(dictionaries, status, productName,storeName,colorStatus);
        Page page = new Page(dCListCount.size(), pageNow);
        List<Dictionaries> dCList = dictionariesService.dictionariesList(dictionaries, status, productName,storeName,colorStatus,page);
        List<StockxStore> stockxStores = stockxStoreService.findList();
        List<StockxStore> list1 = new ArrayList<StockxStore>();
        Set<String> set = new HashSet<String>();
        for (StockxStore stockxStore : stockxStores) {
            if(stockxStore == null){
                continue;
            }
            String storeNames = stockxStore.getName();
            if(storeNames != null){
                if(!set.contains(storeNames)){
                    set.add(storeNames);
                    list1.add(stockxStore);
                }else{
                    continue;
                }
            }
        }

        model.addAttribute("dCList", dCList);
        model.addAttribute("page", page);
        model.addAttribute("status", status);
        model.addAttribute("productName", productName);
        model.addAttribute("colorStatus", colorStatus);
        model.addAttribute("storeName", storeName);
        model.addAttribute("stockxStores", list1);
        model.addAttribute("dictionaries", dictionaries);
        model.addAttribute("linkAddress", linkAddress);
        return "admin/dataAudit/list";
    }

    /**
     * 添加字典标识列
     */
    @RequestMapping("updateDataAudit")
    @ResponseBody
    public Map<String, Object> updateDataAudit(HttpServletRequest request) {
        String dicId = request.getParameter("dicid");
        String identification = request.getParameter("identification");
        String girard = request.getParameter("girard");
        String status = request.getParameter("status");
        if("2".equals(status) || "3".equals(status)){
            identification = "";
            girard = "";
        }
        Dictionaries dictionaries = dictionariesService.findById(dicId);
        int result = 0;
        if (dictionaries != null) {
            //修改该方法
            dictionaries.setIdentification(identification);
            dictionaries.setGirard(girard);
            if(!"".equals(status)){
                if("-1".equals(status)){
                    dictionaries.setStatus(null);
                }else{
                    dictionaries.setStatus(status);
                }
            }
            dictionaries.setUpdatetime(DateUtils.getCurrentTimeAsString());
            result = dictionariesService.update(dictionaries);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("result", result);
        return params;
    }

    @RequestMapping("updateDictionResult")
    @ResponseBody
    public Map<String,Object> updateDictionResult(HttpServletRequest request){
        Map<String,Object> params = new HashMap<String,Object>();
        Dictionaries record = new Dictionaries();
        Dictionaries dictionaries = new Dictionaries();
        int resultCount = 0;

        record.setId(request.getParameter("id"));
        record.setGirard(request.getParameter("girard"));
        record.setIdentification(request.getParameter("identification"));

        dictionaries = dictionariesService.findById(record.getId());
        if (dictionaries != null) {
            resultCount = dictionariesService.update(record);
            if (resultCount > 0){
                commodityDataService.updateResultData(dictionaries.getIdentification(),record.getIdentification());
            }
        }

        params.put("resultCount",resultCount);
        return params;
    }
}
