package com.vstock.admin.base.util;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * Created by administor on 2016/5/30.
 */
@DisallowConcurrentExecution
public class QuartzJobFactory extends QuartzJobBean {
    Logger log = Logger.getLogger(getClass());

    public void query(){
        log.info(" log "+new Date());
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        query();
    }
}
