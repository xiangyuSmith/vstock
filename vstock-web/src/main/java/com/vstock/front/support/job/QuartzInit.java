package com.vstock.front.support.job;

import com.vstock.db.entity.CustomJob;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzInit {

    final static Logger logger = Logger.getLogger(QuartzInit.class);

    public static void init(){
        CustomJob job = new CustomJob();
        job.setJobId("10001");
        job.setJobName("trade_bid_list");
        job.setJobGroup("bid");
        job.setJobStatus(1);
        job.setCronExpression("0/5 * * * * ?");
        quartzOvr(job);
    }

    public static void initTimer(){
        CustomJob job = new CustomJob();
        job.setJobId("10002");
        job.setJobName("initTimer");
        job.setJobGroup("b_rose_id");
        job.setJobStatus(1);
        //TODO 时间更新
//        job.setCronExpression("0/5 * * * * ?");
//        quartzOvr(job);
    }

    private static void quartzOvr(CustomJob job){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Scheduler scheduler = (Scheduler) ctx.getBean("schedulerFactoryBean");
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }catch (SchedulerException se){
            logger.warn(se.getMessage());
        }
    }
}
