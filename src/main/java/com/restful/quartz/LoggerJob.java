package com.restful.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @program: restfulproject
 * @description: description
 * @author: lw
 * @create: 2019-01-29 17:03
 **/
public class LoggerJob extends QuartzJobBean {

    private static final Logger logger = LogManager.getLogger(LoggerJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
          logger.info("--- 10s 执行一次 ---");
    }
}
