package com.vstock.spider.data.controller;

import com.vstock.db.entity.CustomJob;
import com.vstock.spider.data.service.CustomJobService;
import com.vstock.spider.data.spider.quartz.QuartzJobFactoryImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangyu on 2016/6/15.
 */
@Controller
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    CustomJobService customJobService;

    /**
     * 定时器管理列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("quartzList")
    public String quartz(HttpServletRequest request, ModelMap model) {
        List<CustomJob> customJobList = customJobService.getAllJob();
        model.addAttribute("customJobList", customJobList);
        return "admin/quartz/list";
    }

    @RequestMapping("index")
    @ResponseBody
    public Map<String, Object> index() throws SchedulerException {
        //schedulerFactoryBean 由spring创建注入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Scheduler scheduler = (Scheduler) ctx.getBean("schedulerFactoryBean");
        //这里获取任务信息数据
        List<CustomJob> jobList = customJobService.getAllJob();
        for (CustomJob job : jobList) {

            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //不存在，创建一个
            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactoryImpl.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);

                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());

                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());

                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();

                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("suc", "1");
        return params;
    }

    //暂停任务
    @RequestMapping("stopTask")
    @ResponseBody
    public Map<String, Object> stopTask(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String jobName = request.getParameter("jobName");
        String jobGroup = request.getParameter("jobGroup");
        //schedulerFactoryBean 由spring创建注入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Scheduler scheduler = (Scheduler) ctx.getBean("schedulerFactoryBean");
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
            params.put("result", 1);
            return params;
        } catch (Exception e) {
            e.getMessage();
        }
        params.put("result", 0);
        return params;
    }

    //恢复任务
    @RequestMapping("recoveryTask")
    @ResponseBody
    public void recoveryTask(CustomJob customJob) {
        //schedulerFactoryBean 由spring创建注入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Scheduler scheduler = (Scheduler) ctx.getBean("schedulerFactoryBean");
        JobKey jobKey = JobKey.jobKey(customJob.getJobName(), customJob.getJobGroup());
        try {
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //删除任务
    @RequestMapping("delTask")
    public void delTask(CustomJob customJob) {
        //schedulerFactoryBean 由spring创建注入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Scheduler scheduler = (Scheduler) ctx.getBean("schedulerFactoryBean");
        JobKey jobKey = JobKey.jobKey(customJob.getJobName(), customJob.getJobGroup());
        try {
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //立即运行任务
    @RequestMapping("startTask")
    public void startTask(CustomJob customJob) {
        //schedulerFactoryBean 由spring创建注入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Scheduler scheduler = (Scheduler) ctx.getBean("schedulerFactoryBean");
        JobKey jobKey = JobKey.jobKey(customJob.getJobName(), customJob.getJobGroup());
        try {
            scheduler.triggerJob(jobKey);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //更新任务的时间表达式
    @RequestMapping("updateTaskTime")
    public void updateTaskTime(CustomJob customJob) {
        //schedulerFactoryBean 由spring创建注入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Scheduler scheduler = (Scheduler) ctx.getBean("schedulerFactoryBean");
        TriggerKey triggerKey = TriggerKey.triggerKey(customJob.getJobName(), customJob.getJobGroup());
        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger = null;
        try {
            trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (Exception e) {
            e.getMessage();
        }
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(customJob.getCronExpression());
        //按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        //按新的trigger重新设置job执行
        try {
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
