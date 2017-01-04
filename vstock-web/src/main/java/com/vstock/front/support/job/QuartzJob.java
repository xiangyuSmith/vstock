package com.vstock.front.support.job;

import com.vstock.db.entity.CustomJob;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {

    final static Logger logger = Logger.getLogger(QuartzJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CustomJob customJob = (CustomJob)context.getMergedJobDataMap().get("scheduleJob");
        logger.info(customJob.getJobName()+"starting ...");

    }
}
