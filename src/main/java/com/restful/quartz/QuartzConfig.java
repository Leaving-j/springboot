package com.restful.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: restfulproject
 * @description: description
 * @author: lw
 * @create: 2019-01-29 16:35
 **/
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail commonJobDetail() {
        return JobBuilder.newJob(SampleJob.class).withIdentity("commonJob").storeDurably().build();
    }

    @Bean
    public Trigger commonJobTrigger() {
        // 简单构建
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever();

        return TriggerBuilder.newTrigger().forJob(commonJobDetail())
                .withIdentity("commonTrigger").withSchedule(scheduleBuilder).build();
    }

    @Bean
    public JobDetail loggerJobDetail() {
        return JobBuilder.newJob(LoggerJob.class).withIdentity("loggerJob").storeDurably().build();
    }

    @Bean
    public Trigger loggerJobTrigger() {
        // cron表达式构建
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ? ");
        return TriggerBuilder.newTrigger().forJob(loggerJobDetail())
                .withIdentity("loggerTrigger").withSchedule(cronScheduleBuilder).build();
    }
}
