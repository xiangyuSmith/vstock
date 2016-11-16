package com.vstock.admin.base.controller;

import com.vstock.admin.base.service.CommodityDataService;
import com.vstock.admin.base.service.StockxStoreService;
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
    @RequestMapping("commodityData")
    public String commodityData(ResultDataFactory resultDataFactory, HttpServletRequest request, ModelMap model) {
        //获取前台传过来的页面参数
        String pageNow = request.getParameter("pageNow");
        String startTime = request.getParameter("startTime");
        if (startTime != null && !"".equals(startTime)){
            startTime = DateUtils.dateTime(startTime);
        }
        String endTime = request.getParameter("endTime");
        if (endTime != null && !"".equals(endTime)){
            endTime = DateUtils.dateTime(endTime);
        }
        String bids = request.getParameter("bids");
        if("".equals(bids) || bids == null){
            resultDataFactory.setBid(0);
        }else{
            resultDataFactory.setBid(Integer.parseInt(bids));
        }

        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
        if (startTime == null || startTime.equals("")) {
            startTime = yesterday.substring(0,yesterday.length()-1);
        }
        model.addAttribute("startTime", startTime);
        startTime = startTime + " 00:00:00";

        if (endTime == null || endTime.equals("")) {
            endTime = yesterday.substring(0,yesterday.length()-1);
        }
        model.addAttribute("endTime", endTime);
        endTime = endTime + " 23:59:59";
        //获取查询总记录数
        Long totalCount = commodityDataService.findResultDataFactoryCount(resultDataFactory, startTime, endTime);
        //传入分页工具类
        Page page = new Page(totalCount.intValue(), pageNow);
        //进行分页查询
        List<ResultDataFactory> commod = this.commodityDataService.findResultDataFactory(resultDataFactory, page, startTime, endTime);
        //获取当前方法名
        String linkAddress = request.getRequestURI() + "?";
        if (resultDataFactory.getBid() != 0) {
            linkAddress += "&bid=" + resultDataFactory.getBid();
        }
        if (StringUtil.isNotBlank(resultDataFactory.getCommodityDataId())) {
            linkAddress += "&brand=" + resultDataFactory.getCommodityDataId();
        }
        if (StringUtil.isNotBlank(resultDataFactory.getProductName())) {
            linkAddress += "&productName=" + resultDataFactory.getProductName();
        }
        if (StringUtil.isNotBlank(resultDataFactory.getGirard())) {
            linkAddress += "&girard=" + resultDataFactory.getGirard();
        }
        if (StringUtil.isNotBlank(startTime)) {
            linkAddress += "&startTime=" + startTime;
        }
        if (StringUtil.isNotBlank(endTime)) {
            linkAddress += "&endTime=" + endTime;
        }
        model.addAttribute("commodityDataList", commod);
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
//        String pageNow  = request.getParameter("pageNow");
        String storeName = request.getParameter("shopName");
        String footage = request.getParameter("footage");
        String storeStockx = request.getParameter("storeStockx");
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
        String statUninx = date2TimeStamp(startTime,"yyyy-MM-dd HH:mm:ss");
        String endUninx = date2TimeStamp(endTime,"yyyy-MM-dd HH:mm:ss");

//        String linkAddress = request.getRequestURI()+"?shopName="+storeName+"&footage="+footage;
        List<ResultData> resultDatalist = new ArrayList<ResultData>();
//        Page page = new Page(1,"10");
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

        for (String stockxStore : nameSet) {
            //根据商品名查询所有结果
            List<ResultData> reslist = commodityDataService.findByProductName(storeName,stockxStore);
            String nowDate = "";
            for (int x = 0; reslist.size() > x ; x++) {
                ResultData res = reslist.get(reslist.size() - (x+1));
                if (!"".equals(nowDate)) {
                    String createDate = DateUtils.compareDateDown(nowDate,res.getCreateTime());
                    if (nowDate.equals(createDate)) {
                        String sizePric = res.getSizePrice().substring(1, res.getSizePrice().length() - 1);
                        String[] strArr = sizePric.split(",");
                        List<String[]> list = new ArrayList<String[]>();
                        String[] sizePrices = new String[]{};
                        int i = 0;
                        //循环取鞋码价格
                        for (int z = 0; z < strArr.length; z++) {
                            String[] sizePrice = strArr[z].split("=");
                            sizePrice = commodityDataService.taobaoShoeCodeRule(sizePrice, footage);
                            //判断尺码是否相等
                            if (sizePrice[0].contains(footage)) {
                                sizePrices = sizePrice;
                                i++;
                                list.add(sizePrices);
                            }
                        }
                        if (i >= 1) {
                            List<ResultData> resultDataListxs = commodityDataService.shopName(list, res, footage);
                            for (ResultData resultData : resultDataListxs) {
                                if (resultData != null) {
                                    int ifNum = 0;
                                    for(ResultData re : resultDatalist){
                                        if(re.getStoreName().equals(resultData.getStoreName()) && re.getSizePrice().equals(resultData.getSizePrice())){
                                            ifNum = 1;
                                        }
                                    }
                                    if(ifNum != 1){
                                        //判断时间statUninx endUninx
                                        Long statUninxL = Long.valueOf(0);
                                        Long endUninxL = Long.valueOf(2099999999);
                                        if(!"".equals(statUninx)){
                                            statUninxL = Long.parseLong(statUninx);
                                        }
                                        if(!"".equals(endUninx)){
                                            endUninxL = Long.parseLong(endUninx);
                                        }
                                        Long nowDateUninx = Long.parseLong(date2TimeStamp(resultData.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                                        if(statUninxL < nowDateUninx && nowDateUninx < endUninxL){
                                            resultDatalist.add(resultData);
                                        }

                                    }

                                }
                            }
                        }
                    }
                }else {
                    String sizePric = res.getSizePrice().substring(1, res.getSizePrice().length() - 1);
                    String[] strArr = sizePric.split(",");
                    List<String[]> list = new ArrayList<String[]>();
                    String[] sizePrices = new String[]{};
                    int i = 0;
                    //循环取鞋码价格
                    for (int z = 0; z < strArr.length; z++) {
                        String[] sizePrice = strArr[z].split("=");
                        sizePrice = commodityDataService.taobaoShoeCodeRule(sizePrice, footage);
                        //判断尺码是否相等
                        if (sizePrice[0].contains(footage)) {
                            sizePrices = sizePrice;
                            i++;
                            list.add(sizePrices);
                        }
                    }
                    if (i >= 1) {
                        List<ResultData> resultDataList = commodityDataService.shopName(list, res, footage);
                        for (ResultData resultData : resultDataList) {
                            if (resultData != null) {
                                //判断时间statUninx endUninx
                                Long statUninxL = Long.valueOf(0);
                                Long endUninxL = Long.valueOf(2099999999);
                                if(!"".equals(statUninx)){
                                    statUninxL = Long.parseLong(statUninx);
                                }
                                if(!"".equals(endUninx)){
                                    endUninxL = Long.parseLong(endUninx);
                                }
                                Long nowDateUninx = Long.parseLong(date2TimeStamp(resultData.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                                if(statUninxL < nowDateUninx && nowDateUninx < endUninxL){
                                    resultDatalist.add(resultData);
                                }
                                nowDate = res.getCreateTime().substring(0,10) +" 00:00:00";
                            }
                        }
                    }
                }
            }
        }

        model.addAttribute("storeName",storeName);
        model.addAttribute("footage",footage);
//        model.addAttribute("page",page);
//        model.addAttribute("linkAddress",linkAddress);
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
        String statUninx = date2TimeStamp(startTime,"yyyy-MM-dd HH:mm:ss");
        String endUninx = date2TimeStamp(endTime,"yyyy-MM-dd HH:mm:ss");


        ResultData resultData = new ResultData();
        resultData.setStoreName(storeName);
        resultData.setProductName(productName);
        //根据商品名查询所有结果
        List<ResultData> resultDatalist = getResultDataList(productName,storeName,footage,statUninx,endUninx);

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

        if(startTime == null){startTime = ""; }
        if(endTime == null){ endTime = "";}
        if(!"".equals(startTime)){
            startTime = DateUtils.dateTime(startTime);
            startTime = startTime + " 00:00:00";
        }
        if(!"".equals(endTime)){
            endTime = DateUtils.dateTime(endTime);
            endTime = endTime + " 23:59:59";
        }
        String statUninx = date2TimeStamp(startTime,"yyyy-MM-dd HH:mm:ss");
        String endUninx = date2TimeStamp(endTime,"yyyy-MM-dd HH:mm:ss");

        String sizeView = request.getParameter("sizeViews");
        String[] sizeViews = sizeView.split(",");
        ResultData resultData = new ResultData();
        resultData.setStoreName(storeName);
        resultData.setProductName(productName);
        //获取该店铺商品数据
        List<ResultData> resultDataList = commodityDataService.findResultDataAll(resultData);
        //时间
        List<Object> timeList= new ArrayList<Object>();
        //价格
        List<Object> priceList = new ArrayList<Object>();
        List<String[]> rePriceList = new ArrayList<String[]>();

        if(resultDataList.size() != 0){
            //遍历结果集
            for (String size :sizeViews) {
                String[] strResult = new String[1024];
                String strTime = "";
                int xnum = 0;
                for (ResultData reData: resultDataList) {
                    String sizePric = reData.getSizePrice().substring(1,reData.getSizePrice().length()-1);
                    String[] strArr = sizePric.split(",");
                    int i = 0;
                    if(i<30) {
                        i++;
                        int x = 0;
                        List<String[]> list = new ArrayList<String[]>();
                        String[] sizePrices = new String[]{};
                        //循环取鞋码价格
                        for (int z = 0; z < strArr.length; z++) {
                            String[] sizePrice = strArr[z].split("=");
                            sizePrice = commodityDataService.taobaoShoeCodeRule(sizePrice,footage);
                            //判断尺码是否相等
                            if (sizePrice[0].contains(footage)) {
                                //校验是否匹配选中的类型
                                if(sizePrice[0].equals(size)){
                                    x++;
                                    sizePrices = sizePrice;
                                    list.add(sizePrices);
                                }
                            }
                        }
                        if (x >= 1){
                            reData = commodityDataService.individualPrice(list,reData,footage);
                            if(reData != null) {
                                //判断时间
                                Long statUninxL = Long.valueOf(0);
                                Long endUninxL = Long.valueOf(2099999999);
                                if(!"".equals(statUninx)){
                                    statUninxL = Long.parseLong(statUninx);
                                }
                                if(!"".equals(endUninx)){
                                    endUninxL = Long.parseLong(endUninx);
                                }
                                Long createTime = Long.parseLong(date2TimeStamp(reData.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                                if(statUninxL < createTime && createTime < endUninxL){
                                    timeList.add(reData.getCreateTime());
                                    strResult[xnum] = reData.getReservedField()+"!"+reData.getCreateTime();
                                    xnum++;
                                }

                            }
                        }
                    }
                }
                if(!"".equals(strResult[0]) && strResult[0]!=null){
                    String resultStr = "";
                    for (String str:strResult) {
                        if("".equals(str) || str == null){
                            break;
                        }
                        resultStr = resultStr + str + ",";
                    }
                    resultStr = resultStr.substring(0,resultStr.length()-1);
                    String[] resultPrices = resultStr.split(",");
                    rePriceList.add(resultPrices);
                }
            }
        }
        List<String[]> result = new ArrayList<String[]>();
        for (String[] prices : rePriceList) {
            String[] resuPrices = new String[1024];
            String resuStr = "";
            String[] priSS = new String[2];
            int ii = 0;
            for (Object time:timeList) {
                int iix = 0;
                for (String pri : prices) {
                    String times = String.valueOf(time);
                    priSS = pri.split("!");
                    //如果时间匹配则存在
                    if(times.equals(priSS[1])){
                        iix = 1;
                    }
                }
                if(iix == 1){
                    resuPrices[ii] = priSS[0];
                    resuStr = resuStr + priSS[0] + ",";
                }else{
                    resuPrices[ii] = "0";
                    resuStr = resuStr + 0 + ",";
                }
                ii++;
            }
            resuStr = resuStr.substring(0,resuStr.length()-1);
            result.add(resuPrices);
        }

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("result",result);

        params.put("sizeViewsResult",sizeViews);
        params.put("timeList",timeList);
        params.put("priceList",priceList);
        return params;
    }

    @RequestMapping("findLineGraph")
    @ResponseBody
    public Map<String, Object> findLineGraph(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        //初始化变量
        ResultData record = new ResultData();
        List<ResultData> resultDataList = new ArrayList<ResultData>();
//        List<CommodityDetailys> commList = new ArrayList<CommodityDetailys>();
        Map<String, Map<String, CommodityDetailys>> sizeMap = new HashMap<String, Map<String, CommodityDetailys>>();
        String str = "";
        String[] arr = new String[]{};
        //获取当前时间
        Date date = new Date();
        //获取前台传入的参数
        String colorly = request.getParameter("colorly");
        String footage = request.getParameter("footage");
        Integer dateTime = Integer.parseInt(request.getParameter("dateTime"));
        record.setGirard(request.getParameter("colorly"));
        record.setProductName(request.getParameter("commodityName"));
        record.setId(request.getParameter("id"));
        //尺码数量
        int sizeNum = 0;
        //判断鞋码是否为空
        if (footage != null && !"".equals(footage)) {
            //分割获取值
            String[] strfootage = footage.split(",");
            sizeNum = strfootage.length;
            for (int x = 0; x < sizeNum; x++) {
                record.setSizePrice(strfootage[x]);
                List<List> list = new ArrayList<List>();
                //获取一个月前的时间
                Date thdate = DateUtils.wantToLose(date, dateTime);
                for (int i = 0; i <= dateTime; i++) {
                    //拼接日期
                    String startTime = DateUtils.dateToString(DateUtils.addDaysToDate(thdate, i), "yyyy-MM-dd") + " 00:00:00";
                    String endTime = DateUtils.dateToString(DateUtils.addDaysToDate(thdate, i), "yyyy-MM-dd") + " 23:59:59";
                    //查询所有数据
                    resultDataList = commodityDataService.findResultDataTime(record, startTime, endTime);
                    //判断是否存在值
                    if (resultDataList.size() != 0) {//存在
                        List<ResultData> resultDatas = commodityDataService.verificationDateShoeCode(resultDataList,record.getSizePrice());
                        if (resultDatas.size() != 0) {
                            //添加并记录时间
                            list.add(resultDataList);
                            str = str + "," + DateUtils.dateToString(DateUtils.addDaysToDate(thdate, i)).substring(6, 10);
                        }
                    }
                }
                if (str.length() > 1) {
                    //X轴日期存入数组
                    arr = str.substring(1, str.length()).split(",");
                    arr = StringUtil.array_unique(arr);
                    Arrays.sort(arr);
                }

                Map<String, CommodityDetailys> priceMap = new HashMap<String, CommodityDetailys>();
                String createDate = "";

                //初始化变量，并且获取尺码颜色的值
                CommodityDetailys commodityDetailys = new CommodityDetailys();
                commodityDetailys.setFootage(strfootage[x]);
                commodityDetailys.setColorly(colorly);
                if (list.size() > 0) {
                    //循环取值
                    for (List<ResultData> commlist : list) {
                        //初始化变量用于结果算法
                        int a = 0;
                        Double price = Double.parseDouble("0");
                        CommodityDetailys commodityDetailysT = new CommodityDetailys();
                        //遍历取值
                        for (int y = 0; y < commlist.size(); y++) {
                            ResultData resultData = commlist.get(y);
                            float b = StringUtil.getSimilarityRatio(resultData.getProductName(), resultData.getProductName());
                            if (b == 1.0) {
                                //去掉鞋码前面{}
//                                String averagePrice = resultData.getSizePrice().substring(1, resultData.getSizePrice().length() - 1);
                                //截取，左右的值
                                String[] strArr = resultData.getSizePrice().split(",");
                                int i = 0;
                                List<String[]> sizapricelist = new ArrayList<String[]>();
                                String[] sizePrices = new String[]{};
                                //循环取鞋码价格
                                for (int z = 0; z < strArr.length; z++) {
                                    String[] sizePrice = strArr[z].split("=");
                                    if (!"".equals(commodityDetailys.getFootage()) && commodityDetailys.getFootage() != null) {
                                        sizePrice = commodityDataService.taobaoShoeCodeRule(sizePrice, commodityDetailys.getFootage());
                                        //判断尺码是否相等
                                        if (sizePrice[0].contains(commodityDetailys.getFootage())) {
                                            i++;
                                            sizePrices = sizePrice;
                                            sizapricelist.add(sizePrice);
                                        }
                                    }
                                }
                                if (i >= 1) {
                                    String[] strings = commodityDataService.judgeprice(sizapricelist, commodityDetailys);
                                    if (strings.length > 1) {
                                        a = a + Integer.parseInt(strings[1]);
                                        price = price + Double.parseDouble(strings[0]);
                                        createDate = resultData.getCreateTime();
                                    }
                                }
                            }
                        }
                        if (price > 0) {
                            price = price / a;
                            commodityDetailys.setAveragePrice(price.toString());
                            String shopName = commodityDetailys.getShopName();
                            String createDates = commodityDetailys.getCreateDate();
                            String footages = commodityDetailys.getFootage();
                            String averagePrice = commodityDetailys.getAveragePrice();
                            String colorlys = commodityDetailys.getColorly();
                            commodityDetailysT.setShopName(shopName);
                            commodityDetailysT.setCreateDate(createDates);
                            commodityDetailysT.setFootage(footages);
                            commodityDetailysT.setAveragePrice(averagePrice);
                            commodityDetailysT.setColorly(colorlys);
                            priceMap.put(createDate,commodityDetailysT);
                        }
                    }
                }
//                else {
//                    commodityDetailys.setAveragePrice(price.toString());
//                    priceMap.put(createDate,commodityDetailys);
//                }
                sizeMap.put(record.getSizePrice(),priceMap);
            }
        }

        //封装前台数据
        Map<String, Object> params = new HashMap<String, Object>();
        params = commodityDataService.shoeCodeChartMosaic(sizeMap,arr);
//        params.put("commList", commList);
//        params.put("arr", arr);
//        params.put("sizeNum", sizeNum);
        return params;
    }

    public List<ResultData> getResultDataList(String productName,String storeName,String footage,String statUninx,String endUninx){
        List<ResultData> resultDatalist = new ArrayList<ResultData>();
        List<ResultData> reslist = commodityDataService.findByProductName(productName,storeName);
        String nowDate = "";
        for (int x = 0; reslist.size() > x ; x++) {
            ResultData res = reslist.get(reslist.size() - (x+1));
            if (!"".equals(nowDate)) {
                String createDate = DateUtils.compareDateDown(nowDate,res.getCreateTime());
                if (nowDate.equals(createDate)) {
                    String sizePric = res.getSizePrice().substring(1, res.getSizePrice().length() - 1);
                    String[] strArr = sizePric.split(",");
                    List<String[]> list = new ArrayList<String[]>();
                    String[] sizePrices = new String[]{};
                    int i = 0;
                    //循环取鞋码价格
                    for (int z = 0; z < strArr.length; z++) {
                        String[] sizePrice = strArr[z].split("=");
                        sizePrice = commodityDataService.taobaoShoeCodeRule(sizePrice, footage);
                        //判断尺码是否相等
                        if (sizePrice[0].contains(footage)) {
                            sizePrices = sizePrice;
                            i++;
                            list.add(sizePrices);
                        }
                    }
                    if (i >= 1) {
                        List<ResultData> resultDataListxs = commodityDataService.shopName(list, res, footage);
                        for (ResultData resultData : resultDataListxs) {
                            if (resultData != null) {
                                int ifNum = 0;
                                for(ResultData re : resultDatalist){
                                    if(re.getStoreName().equals(resultData.getStoreName()) && re.getSizePrice().equals(resultData.getSizePrice())){
                                        ifNum = 1;
                                    }
                                }
                                if(ifNum != 1){
                                    resultDatalist.add(resultData);
                                }

                            }
                        }
                    }
                }
            }else {
                String sizePric = res.getSizePrice().substring(1, res.getSizePrice().length() - 1);
                String[] strArr = sizePric.split(",");
                List<String[]> list = new ArrayList<String[]>();
                String[] sizePrices = new String[]{};
                int i = 0;
                //循环取鞋码价格
                for (int z = 0; z < strArr.length; z++) {
                    String[] sizePrice = strArr[z].split("=");
                    sizePrice = commodityDataService.taobaoShoeCodeRule(sizePrice, footage);
                    //判断尺码是否相等
                    if (sizePrice[0].contains(footage)) {
                        sizePrices = sizePrice;
                        i++;
                        list.add(sizePrices);
                    }
                }
                if (i >= 1) {
                    List<ResultData> resultDataList = commodityDataService.shopName(list, res, footage);
                    for (ResultData resultDatax2 : resultDataList) {
                        if (resultDatax2 != null) {
                            resultDatalist.add(resultDatax2);
                            nowDate = res.getCreateTime().substring(0,10) +" 00:00:00";
                        }
                    }
                }
            }
        }
        return resultDatalist;
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            if("".equals(date_str)){
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
