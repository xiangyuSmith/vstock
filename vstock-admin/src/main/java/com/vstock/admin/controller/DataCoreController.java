package com.vstock.admin.controller;

import com.vstock.admin.service.CommodityDataService;
import com.vstock.admin.service.StockxStoreService;
import com.vstock.db.entity.*;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xiangyu on 2016/7/18.
 */
@Controller
@RequestMapping("/dataCore")
public class DataCoreController {

    private static Logger logger = Logger.getLogger(DataCoreController.class);

    @Autowired
    CommodityDataService commodityDataService;
    @Autowired
    StockxStoreService stockxStoreService;

    /**
     * 球鞋数据分析查询
     *
     * @param resultDataFactory
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(ResultDataFactory resultDataFactory, HttpServletRequest request, ModelMap model) {
        List<String> list = new ArrayList<String>();
        String pageNow = request.getParameter("pageNow");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String bids = request.getParameter("bids");
        if("".equals(bids) || bids == null){
            resultDataFactory.setBid(0);
        }else{
            resultDataFactory.setBid(Integer.parseInt(bids));
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
        if (startTime == null || startTime.equals("")) {
            startTime = yesterday.substring(0,yesterday.length()-1);
        }
        if (endTime == null || endTime.equals("")) {
            endTime = yesterday.substring(0,yesterday.length()-1);
        }
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        list.add("&startTime="+startTime);
        list.add("&endTime="+endTime);
        startTime = startTime + " 00:00:00";
        endTime = endTime + " 23:59:59";
        String linkAddress = request.getRequestURI() + "?1=1";
        linkAddress = commodityDataService.linkAddress(linkAddress,list,resultDataFactory);
        int totalCount = commodityDataService.findResultDataFactoryCount(resultDataFactory, startTime, endTime);
        Page page = new Page(totalCount, pageNow);
        List<ResultDataFactory> resultDataFactoryList = commodityDataService.findResultDataFactoryAll(resultDataFactory, startTime, endTime,page);
        model.addAttribute("commodityDataList", resultDataFactoryList);
        model.addAttribute("page", page);
        model.addAttribute("resultData", resultDataFactory);
        model.addAttribute("linkAddress", linkAddress);
        return "admin/storeAnalysis/commodityShow";
    }


    /**
     * 查询尺码数据
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("findMeasurement")
    public String findMeasurement(HttpServletRequest request, ModelMap model) {
        //初始化变量
        ResultData record = new ResultData();
        List<CommodityDetailys> commodityDetailysList = new ArrayList<CommodityDetailys>();

        //获取前台传入的值
        record.setProductName(request.getParameter("commodityName"));
        //获取当前方法名
        String linkAddress = request.getRequestURI() + "?commodityName=" + record.getProductName();

        commodityDetailysList = commodityDataService.shoesCodeInformation(record);
        if (commodityDetailysList.size() > 0) {
            record.setGirard(commodityDetailysList.get(0).getColorly());
        }

        StringBuffer stringBuffer = commodityDataService.shoeCodeInformation(commodityDetailysList);

        model.addAttribute("stringBuffer", stringBuffer);
        model.addAttribute("resultData", record);
        model.addAttribute("linkAddress", linkAddress);
        return "admin/storeAnalysis/sizeDataList";
    }

    /**
     * 店铺鞋码价格趋势分析
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("storeDataAnalysis")
    public String storeDataAnalysis(HttpServletRequest request, ModelMap model) {
        String storeName = request.getParameter("shopName");
        String footage = request.getParameter("footage");
        String storeStockx = request.getParameter("storeStockx");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if(startTime == null){startTime = ""; }
        if(endTime == null){ endTime = "";}
        model.addAttribute("endTime",endTime);
        model.addAttribute("startTime",startTime);
        //获取店铺名称
        Set<String> nameSet = new HashSet<String>();
        if("".equals(storeStockx) || storeStockx == null){
            List<StockxStore> stockxStores = stockxStoreService.findList();
            //店铺名称去重
            for (int i = 0 ; i < stockxStores.size(); i++){
                nameSet.add(stockxStores.get(i).getName());
            }
            model.put("storeStockx","");
        }else{
            nameSet.add(storeStockx);
            model.put("storeStockx",storeStockx);
        }
        List<ResultData> resultDatalist = commodityDataService.storeDataAnalysis(nameSet,startTime,endTime,storeName,footage);
        model.addAttribute("storeName",storeName);
        model.addAttribute("footage",footage);
        model.addAttribute("resultDatalist",resultDatalist);
        return "admin/storeAnalysis/storeDataAnalysis";
    }

    /**
     * 查看店铺尺码月趋势
     */
    @RequestMapping("getStoreTrend")
    public String getStoreTrend(HttpServletRequest request,ModelMap model){
        //获取店铺ID 和 商品名称
        String storeName = request.getParameter("storeName");
        String productName = request.getParameter("productName");
        String footage = request.getParameter("footage");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if(startTime == null){startTime = ""; }
        if(endTime == null){ endTime = "";}
        model.addAttribute("endTime",endTime);
        model.addAttribute("startTime",startTime);
        if(!"".equals(startTime)){
            startTime = DateUtils.dateTime(startTime);
            startTime = startTime + " 00:00:00";
        }
        if(!"".equals(endTime)){
            endTime = DateUtils.dateTime(endTime);
            endTime = endTime + " 23:59:59";
        }
        String statUninx = DateUtils.date2TimeStamp(startTime,"yyyy-MM-dd HH:mm:ss");
        String endUninx = DateUtils.date2TimeStamp(endTime,"yyyy-MM-dd HH:mm:ss");
        ResultData resultData = new ResultData();
        resultData.setStoreName(storeName);
        resultData.setProductName(productName);
        //根据商品名查询所有结果
        List<ResultData> resultDatalist = commodityDataService.getResultDataList(resultData,footage,statUninx,endUninx);
        model.addAttribute("resultDatalist",resultDatalist);
        model.put("footage",footage);
        model.put("productName",productName);
        model.put("storeName",storeName);
        return "admin/storeAnalysis/storeTrend";
    }

    @RequestMapping("getResultDataLineGraph")
    @ResponseBody
    public Map<String, Object> getResultDataLineGraph(HttpServletRequest request, HttpServletResponse response) {
        //获取店铺ID 和 商品名称
        String storeName = request.getParameter("storeName");
        String productName = request.getParameter("productName");
        String footage = request.getParameter("footage");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String sizeView = request.getParameter("sizeViews");
        if(startTime == null){startTime = ""; }
        if(endTime == null){ endTime = "";}
        Map<String, Object> params = commodityDataService.getResultDataLineGraph(storeName,productName,footage,startTime,endTime,sizeView);
        return params;
    }

    @RequestMapping("findLineGraph")
    @ResponseBody
    public Map<String, Object> findLineGraph(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        //初始化变量
        ResultData record = new ResultData();

        //获取前台传入的参数
        String colorly = request.getParameter("colorly");
        String footage = request.getParameter("footage");
        Integer dateTime = Integer.parseInt(request.getParameter("dateTime"));
        record.setGirard(request.getParameter("colorly"));
        record.setProductName(request.getParameter("commodityName"));
        record.setId(request.getParameter("id"));

        //封装前台数据
        Map<String, Object> params = commodityDataService.findLineGraph(record,colorly,footage,dateTime);
        return params;
    }
}
