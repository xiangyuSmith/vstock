package com.vstock.admin.base.test;

import com.vstock.db.entity.CustomJob;
import com.vstock.admin.base.util.QuartzJobFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by administor on 2016/5/30.
 */

public class TsokTest {

    private QuartzJobFactory quartzJobFactory;

//    private static Logger logger = Logger.getLogger(TsokTest.class);

    private Scheduler createScheduler() throws SchedulerException{
        return new StdSchedulerFactory().getScheduler();
    }

    private static Map<String, CustomJob> jobMap = new HashMap<String, CustomJob>();
    public static void main(String[]args){
        for (int i = 0; i < 5; i++){
            CustomJob job = new CustomJob();
            job.setJobId(10001+i+"");
            job.setJobName("commodityDataController");
            job.setJobGroup("index");
            job.setJobStatus(0);
            job.setCronExpression("0/5 * * * * ?");
            job.setMemos("测试任务");
            addJob(job);
        }
    }
    /**
     * 添加任务
     */
    public static void addJob(CustomJob customJob){
        jobMap.put(customJob.getJobGroup() + "_" + customJob.getJobName(), customJob);
    }
}
