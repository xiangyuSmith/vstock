package com.vstock.spider.data.spider.quartz;

import com.vstock.ext.util.ConstUtil;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@DisallowConcurrentExecution
public class QuartzJobFactoryImpl implements Job
{

    private static Logger log = Logger.getLogger(QuartzJobFactoryImpl.class);

    private static Integer timeThread = Integer.parseInt(ConstUtil.getSpiderProperties().getProperty("timeThread"));

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
        Integer numberThread = Integer.parseInt(ConstUtil.getSpiderProperties().getProperty("numberThread"));
        List<StockxStore> finalList = stockxStoreService.finalList();
        //获取机器数量 & 机器号
        int listSize = finalList.size();
        int machineCount = listSize / Integer.parseInt(ConstUtil.getSpiderProperties().getProperty("machineCount"));
        int machineNum = Integer.parseInt(ConstUtil.getSpiderProperties().getProperty("machineNum"));
        //按机器数平局分配
        int subCount = listSize % machineCount == 0 ? listSize / machineCount : listSize / machineCount + 1;
        int startIndext = 0;
        int stopIndext = 0;
        if (subCount == 1) {
            threadStockx(finalList, numberThread);
        } else {
            for (int k = 0; k < subCount; k++) {
                stopIndext = (k == subCount - 1) ? stopIndext + listSize % machineCount : stopIndext + machineCount;
                List<StockxStore> tempList = new ArrayList<StockxStore>(finalList.subList(startIndext, stopIndext));
                startIndext = stopIndext;
                if (machineNum - 1 == k) {
                    //处理当前集合
                    threadStockx(tempList, numberThread);
                } else {
                    if (k == subCount - 1) {
                        //判断是否有余
                        if (listSize % machineCount != 0) {
                            if (machineNum == 1) {
                                //处理当前集合
                                threadStockx(tempList, numberThread);
                            }
                        }
                    }
                }
            }
        }
    }

    public void threadStockx(List<StockxStore> tempList, int numberThread) {
        MyThread.setFinalList(tempList);
        if (tempList.size() >= numberThread) {
            try {
                for (int i = 0; i < numberThread; i++) {
                    String name = i + "-" + numberThread;
                    MyThread t1 = new MyThread(name);
                    t1.start();
                    Thread.sleep(timeThread);
                }
            }catch (InterruptedException e){
                log.warn(e.getMessage());
            }
        } else {
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