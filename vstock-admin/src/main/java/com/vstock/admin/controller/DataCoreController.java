package com.vstock.admin.controller;

import com.vstock.admin.service.BasicinformationService;
import com.vstock.admin.service.CommodityDataService;
import com.vstock.admin.service.StockxStoreService;
import com.vstock.db.entity.*;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * Created by xiangyu on 2016/7/18.
 */
@Controller
@RequestMapping("/dataCore")
public class DataCoreController {

    private static Logger logger = Logger.getLogger(DataCoreController.class);

    @Autowired
    BasicinformationService basicinformationService;
    @Autowired
    CommodityDataService commodityDataService;
    @Autowired
    StockxStoreService stockxStoreService;
    private Statement stmt;

    /**
     * 球鞋数据分析查询
     *
     * @param resultData
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(ResultData resultData, HttpServletRequest request, ModelMap model) {
        long zz,yy,xx= System.currentTimeMillis();

        List<String> list = new ArrayList<String>();
        String pageNow = request.getParameter("pageNow");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String bids = request.getParameter("bids");
        if ("".equals(bids) || bids == null) {
            resultData.setBid(0);
        } else {
            resultData.setBid(Integer.parseInt(bids));
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
        if (startTime == null || startTime.equals("")) {
            startTime = yesterday.substring(0, yesterday.length() - 1);
        }
        if (endTime == null || endTime.equals("")) {
            endTime = yesterday.substring(0, yesterday.length() - 1);
        }
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        list.add("&startTime=" + startTime);
        list.add("&endTime=" + endTime);
        startTime = startTime + " 00:00:00";
        endTime = endTime + " 23:59:59";
        String linkAddress = request.getRequestURI() + "?1=1";
        linkAddress = commodityDataService.linkAddress(linkAddress, list, resultData);

        Connection con;
        Connection con2;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/stockx";
        //MySQL配置时的用户名
        String user = "test";
        //MySQL配置时的密码
        String password = "123123";
        Map<Integer,ResultData> resultdata_cache = new HashMap<Integer,ResultData>();
        Map<Integer,Basicinformation> basicinfomation_cache = new HashMap<Integer,Basicinformation>();
        //按照basicinformationid分组数据
        Map<Integer,List<ResultData>> group_data = new HashMap<Integer,List<ResultData>>(); //id分组数据

        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            con2 = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");

            yy = System.currentTimeMillis();
            zz = yy - xx;
            logger.info("数据库链接时间:" +  zz);
            Statement stmt =  con.createStatement(); //创建Statement对象
            Statement stmt2 =  con2.createStatement(); //创建Statement对象
            //取值resultdata
            String sql = "select * from resultdata";    //要执行的SQL
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象

            while (rs.next()) {
                ResultData data1 = new ResultData();
                data1.setId(rs.getString(1));
                data1.setBasiciformationId(rs.getString(3));
                data1.setTransactionRecord(rs.getString(10));
                data1.setCreateTime(rs.getString(11));
                resultdata_cache.put(rs.getInt(1),data1);
                Integer basicid = Integer.parseInt(data1.getBasiciformationId());
                //分组
                List<ResultData> data_list = group_data.get(basicid);
                if(data_list==null) {
                    data_list =  new ArrayList<ResultData>();
                }
                data_list.add(data1);
                group_data.put(basicid,data_list);
            }

            //取值basicinfomation
            String sql2 = "select * from basicinformation";    //要执行的SQL
            ResultSet rs2 = stmt2.executeQuery(sql2);//创建数据对象
            while (rs2.next()) {
                Basicinformation data2 = new Basicinformation();
                data2.setId(rs2.getString(1));
                data2.setName(rs2.getString(3));
                data2.setArtNo(rs2.getString(5));
                basicinfomation_cache.put(rs2.getInt(1),data2);
            }
            rs.close();
            stmt.close();
            con.close();
        }
        catch(Exception e) {
            System.out.println("有异常！！");
        }
        finally{
            System.out.println("数据库数据成功获取！！");
        }

        yy = System.currentTimeMillis();
        zz = yy - xx;
        logger.info("数据库查询时间:" +  zz);

        //输出内容
        List<Basicinformation> resultDataFactoryList = new LinkedList<Basicinformation>();
        Integer num = 0;
        //轮询分组内的数据
        Iterator group_data_poniter=group_data.entrySet().iterator();
        Integer key;
        List<ResultData> value;
        //进行分组
        while(group_data_poniter.hasNext()){
            Map.Entry entry2 = (Map.Entry)group_data_poniter.next();
            //key為basicinfomation的鍵值
            key=Integer.parseInt(entry2.getKey().toString());
            value=(List<ResultData>)entry2.getValue();
            //组内排序取出时间最新的数据
            int i = 0;
            ResultData tmpData1 = (ResultData)value.get(i);
            ResultData tmpData2;
            ResultData rd = tmpData1;
            String time1 = tmpData1.getCreateTime();
            for(i=0;i<value.size()-1;i++) {
                tmpData1 = (ResultData)value.get(i);
                tmpData2 = (ResultData)value.get(i+1);
                String time2 = tmpData2.getCreateTime();
                time1 = DateUtils.compareDateUp(time1,time2);
                rd = time1 == time2 ? tmpData2 : tmpData1;
            }
            //选出最新的赋值给resultDataFactoryList
            Integer basicid = (Integer.parseInt(rd.getBasiciformationId())) ;
            Basicinformation bd = basicinfomation_cache.get(basicid);
            bd.setResultData(rd);
            resultDataFactoryList.add(bd);
            num = num + 1;
            //System.out.println("数字为"+num);
            if (num>=group_data.size()-1){
                break;
            }
        }
        yy = System.currentTimeMillis();
        zz = yy - xx;
        logger.info("程序运行时间:" +  zz);

        model.addAttribute("commodityDataList", resultDataFactoryList);
        model.addAttribute("page", pageNow);
        model.addAttribute("resultData", resultData);
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
        if (startTime == null) {
            startTime = "";
        }
        if (endTime == null) {
            endTime = "";
        }
        model.addAttribute("endTime", endTime);
        model.addAttribute("startTime", startTime);
        //获取店铺名称
        Set<String> nameSet = new HashSet<String>();
        if ("".equals(storeStockx) || storeStockx == null) {
            List<StockxStore> stockxStores = stockxStoreService.findList();
            //店铺名称去重
            for (int i = 0; i < stockxStores.size(); i++) {
                nameSet.add(stockxStores.get(i).getName());
            }
            model.put("storeStockx", "");
        } else {
            nameSet.add(storeStockx);
            model.put("storeStockx", storeStockx);
        }
        List<ResultData> resultDatalist = commodityDataService.storeDataAnalysis(nameSet, startTime, endTime, storeName, footage);
        model.addAttribute("storeName", storeName);
        model.addAttribute("footage", footage);
        model.addAttribute("resultDatalist", resultDatalist);
        return "admin/storeAnalysis/storeDataAnalysis";
    }

    /**
     * 查看店铺尺码月趋势
     */
    @RequestMapping("getStoreTrend")
    public String getStoreTrend(HttpServletRequest request, ModelMap model) {
        //获取店铺ID 和 商品名称
        String storeName = request.getParameter("storeName");
        String productName = request.getParameter("productName");
        String footage = request.getParameter("footage");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if (startTime == null) {
            startTime = "";
        }
        if (endTime == null) {
            endTime = "";
        }
        model.addAttribute("endTime", endTime);
        model.addAttribute("startTime", startTime);
        if (!"".equals(startTime)) {
            startTime = DateUtils.dateTime(startTime);
            startTime = startTime + " 00:00:00";
        }
        if (!"".equals(endTime)) {
            endTime = DateUtils.dateTime(endTime);
            endTime = endTime + " 23:59:59";
        }
        String statUninx = DateUtils.date2TimeStamp(startTime, "yyyy-MM-dd HH:mm:ss");
        String endUninx = DateUtils.date2TimeStamp(endTime, "yyyy-MM-dd HH:mm:ss");
        ResultData resultData = new ResultData();
        resultData.setStoreName(storeName);
        resultData.setProductName(productName);
        //根据商品名查询所有结果
        List<ResultData> resultDatalist = commodityDataService.getResultDataList(resultData, footage, statUninx, endUninx);
        model.addAttribute("resultDatalist", resultDatalist);
        model.put("footage", footage);
        model.put("productName", productName);
        model.put("storeName", storeName);
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
        if (startTime == null) {
            startTime = "";
        }
        if (endTime == null) {
            endTime = "";
        }
        Map<String, Object> params = commodityDataService.getResultDataLineGraph(storeName, productName, footage, startTime, endTime, sizeView);
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
        Map<String, Object> params = commodityDataService.findLineGraph(record, colorly, footage, dateTime);
        return params;
    }
}
