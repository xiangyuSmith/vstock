package com.vstock.spider.data.controller;

import com.vstock.db.entity.StockxInfo;
import com.vstock.db.entity.StockxStore;
import com.vstock.spider.data.service.BasicinformationService;
import com.vstock.spider.data.service.CommodityDataService;
import com.vstock.spider.data.service.StockxStoreService;
import com.vstock.spider.data.spider.GithubRepoPageProcessor;
import com.vstock.spider.data.spider.StockxInfoRepoPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2016/5/12.
 */
@Controller
@RequestMapping("/commodityData")
public class CommodityDataController {
    @Autowired
    CommodityDataService commodityDataService;

    @Autowired
    StockxStoreService stockxStoreService;

    @Autowired
    BasicinformationService basicinformationService;

    @RequestMapping("taobaoData")
    public String taobaoData() {
        return "base/index";
    }

    @RequestMapping("index")
    public String index() {
        //获取店铺表中录入的连接地址
        List<StockxStore> list = stockxStoreService.findList();
        List<StockxStore> finalList = new ArrayList<StockxStore>();
        //判断是否存在值
        if (list.size() > 0) {
            //调用爬虫方法
            for (StockxStore stockxStore : list) {
                int num = Integer.parseInt(stockxStore.getPageNo());
                String toUrl = stockxStore.getUrl();
                for (int i = 1; i <= num; i++) {
                    String newUrl = toUrl + "&pageNo=" + i;
                    StockxStore store = new StockxStore();
                    store.setId(stockxStore.getId());
                    store.setBrand(stockxStore.getBrand());
                    store.setPageNo(stockxStore.getPageNo());
                    store.setName(stockxStore.getName());
                    store.setStatus(stockxStore.getStatus());
                    store.setUrl(newUrl);
                    finalList.add(store);
                }
            }
            for (StockxStore stockxStore : finalList) {
                GithubRepoPageProcessor.main(stockxStore, finalList);
            }
        }
        return "base/index";
    }

    @RequestMapping("stockInfo")
    public String stockInfo(HttpServletRequest request) {
        //获取stockx鞋子链接
        List<StockxInfo> stockxInfoList = stockxStoreService.findStockxInfo();
        for (StockxInfo stockxInfo : stockxInfoList) {
            if ("7".equals(stockxInfo.getBrandId()) || "8".equals(stockxInfo.getBrandId()) || "9".equals(stockxInfo.getBrandId()) || "10".equals(stockxInfo.getBrandId())
                    || "11".equals(stockxInfo.getBrandId())) {
                //拼接URL
                String toUrl = "";
                for (int i = 1; i <= stockxInfo.getPageNum(); i++) {
                    toUrl = stockxInfo.getInterfacesApi() + "?page=" + i + "&category=" + stockxInfo.getCategory();
                    StockxInfoRepoPageProcessor.main(toUrl, stockxInfo.getName(), request);
                }
            }
        }
        return "base/index";
    }
//
//    @RequestMapping("resultDataFactory")
//    public String resultDataFactory(HttpServletRequest request){
//        //获取所有鞋库记录
//        Basicinformation basicinformation = new Basicinformation();
//        List<Basicinformation> basicinformationList = basicinformationService.findAll(basicinformation);
//        Calendar   cal   =   Calendar.getInstance();
//        cal.add(Calendar.DATE,   -1);
////        String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
//        String yesterday = "2016-09-13 ";
//        String startTime = yesterday;
//        String endTime = yesterday;
//        if (startTime != null) {
//            if (!startTime.equals("")) {
//                startTime = startTime + "00:00:00";
//            }
//        }
//        if (endTime != null) {
//            if (!endTime.equals("")) {
//                endTime = endTime + "23:59:59";
//            }
//        }
//        //遍历鞋库记录
//        for (Basicinformation ba : basicinformationList) {
//            String artNo = ba.getArtNo();
//
//            //带入货号，查询前一天 resultData 数据，并累加计算销量
//            ResultData resultData = new ResultData();
//            resultData.setGirard(artNo);
//            List<ResultData> resultList = commodityDataService.findResultDataTime(resultData,startTime,endTime);
//            //总销售量
//            int count = 0;
//            if(resultList.size() != 0){
//                ResultData res = resultList.get(0);
//                ResultDataFactory resFactory = new ResultDataFactory();
//                for (ResultData re : resultList) {
//                    if(!"-".equals(re.getTransactionRecord())){
//                        count += Integer.parseInt(re.getTransactionRecord());
//                    }
//                }
//                //存入数据列
//                resFactory.setCommodityDataId(res.getCommodityDataId());
//                resFactory.setProductName(res.getProductName());
//                resFactory.setBrand(res.getBrand());
//                resFactory.setGirard(res.getGirard());
//                resFactory.setSizePrice(res.getSizePrice());
//                resFactory.setTransactionRecord(res.getTransactionRecord());
//                resFactory.setCreateTime(res.getCreateTime());
//                resFactory.setReservedField(res.getReservedField());
//                resFactory.setCountTransactionRecord(String.valueOf(count));
//                List<ResultDataFactory> resultDataFactoryList = commodityDataService.getFactory(res.getProductName());
//                int difference = 0;
//                if(resultDataFactoryList.size() != 0){
//                    difference = count - Integer.parseInt(resultDataFactoryList.get(0).getCountTransactionRecord());
//                }else{
//                    difference = count - 0;
//                }
//                resFactory.setDifference(String.valueOf(difference));
//                //保存最终结果
//                int dataReultBool = commodityDataService.saveResultDataFactory(resFactory);
//            }
//        }
//        return "base/index";
//    }
}
