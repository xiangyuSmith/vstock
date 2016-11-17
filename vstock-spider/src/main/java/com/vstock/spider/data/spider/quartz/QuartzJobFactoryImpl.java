package com.vstock.spider.data.spider.quartz;

import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.ToolSpring;
import com.vstock.spider.data.bean.MyThread;
import com.vstock.db.entity.*;
import com.vstock.spider.data.service.BasicinformationService;
import com.vstock.spider.data.service.CommodityDataService;
import com.vstock.spider.data.service.StockxStoreService;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@DisallowConcurrentExecution
public class QuartzJobFactoryImpl implements Job
{
    Logger log = Logger.getLogger(getClass());

    StockxStoreService stockxStoreService = (StockxStoreService) ToolSpring.getBean("stockxStore");
    BasicinformationService basicinformationService = (BasicinformationService) ToolSpring.getBean("basicinformation");
    CommodityDataService commodityDataService = (CommodityDataService) ToolSpring.getBean("commodityData");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("任务成功运行");
        CustomJob customJob = (CustomJob)context.getMergedJobDataMap().get("scheduleJob");
        System.out.println("任务名称 = [" + customJob.getJobName() + "]");
        if("productData".equals(customJob.getJobGroup())){
            if(customJob.getJobStatus() == 1){
                productData();
            }
        }
        if("userData".equals(customJob.getJobGroup())){
            if(customJob.getJobStatus() == 1){
                userData();
            }
        }
        if("resultDataFactory".equals(customJob.getJobGroup())){
            if(customJob.getJobStatus() == 1){
                resultDataFactory();
            }
        }
    }

    //数据爬取
    private void productData(){
        log.debug("Get data for time：" + DateUtils.getCurrentTimeAs14String());
        //获取店铺表中录入的连接地址
//        List<StockxStore> finalList = new ArrayList<StockxStore>();
//        List<StockxStore> list = stockxStoreService.findList();
//        //判断是否存在值
//        if (list.size() >0) {
//            //调用爬虫方法
//            for (StockxStore stockxStore :list) {
//                int num = Integer.parseInt(stockxStore.getPageNo());
//                String toUrl = stockxStore.getUrl();
//                for (int i = 1;i<=num;i++){
//                    String newUrl = toUrl+"&pageNo="+i;
//                    StockxStore store = new StockxStore();
//                    store.setId(stockxStore.getId());
//                    store.setBrand(stockxStore.getBrand());
//                    store.setPageNo(stockxStore.getPageNo());
//                    store.setName(stockxStore.getName());
//                    store.setStatus(stockxStore.getStatus());
//                    store.setUrl(newUrl);
//                    finalList.add(store);
//                }
//            }
//            for (StockxStore stockxStore:finalList) {
//                GithubRepoPageProcessor.main(stockxStore,finalList);
//            }
//        }
        Integer numberThread = 2;
        List<StockxStore> finalList = stockxStoreService.finalList();
        if (finalList.size() >= numberThread){
            for (int i = 0; i < numberThread; i++){
                String name = i + "-" + numberThread;
                MyThread t1 = new MyThread(name);
                t1.start();
                try {
                    Thread.sleep(10000);
                }catch (Exception e){
                    log.debug(e.getMessage());
                }
            }
        }else {
            MyThread t1 = new MyThread("MyThread1");
            t1.start();
        }
    }

    //数据处理工厂
    public void resultDataFactory(){
//        log.debug("resultData Factory start for time：" + DateUtils.getCurrentTimeAs14String());
//        //获取所有鞋库记录
//        Basicinformation basicinformation = new Basicinformation();
//        List<Basicinformation> basicinformationList = basicinformationService.findAll(basicinformation);
//        Calendar cal   =   Calendar.getInstance();
//        cal.add(Calendar.DATE,   -1);
//        String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
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
//                        log.debug("start quartz ...");
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
//                //保存最终结果
//                int dataReultBool = commodityDataService.saveResultDataFactory(resFactory);
//            }
//        }
    }

    //更新用户
    private void userData(){
        log.debug("更新用户,时间：" + DateUtils.getCurrentTimeAs14String());
    }

}