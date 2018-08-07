package com.kotall.oms.weixin.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 应用调度器
 *
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/2/13
 */
@Component
public class DaemonScheduler {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        startRefreshWeiXinTokenJob(scheduler);
    }

    private void startRefreshWeiXinTokenJob(Scheduler scheduler) throws SchedulerException {
        // 每隔2小时执行一次
        JobDetail jobDetail = JobBuilder.newJob(RefreshAccessTokenJob.class)
                .withIdentity(JobConstants.WX_ACCESS_TOKEN_REFRESH_JOB, JobConstants.WX_ACCESS_TOKEN_REFRESH_GROUP)
                .build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0/2 * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(JobConstants.WX_ACCESS_TOKEN_REFRESH_TRIGGER, JobConstants.WX_ACCESS_TOKEN_REFRESH_GROUP)
                .withSchedule(scheduleBuilder).build();

        // 启动时立即执行一次
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0);
        Trigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity(JobConstants.WX_ACCESS_TOKEN_REFRESH_TRIGGER_SIMPLE,JobConstants.WX_ACCESS_TOKEN_REFRESH_GROUP)
                .withSchedule(simpleScheduleBuilder).build();

        Set<Trigger> triggers = new HashSet<>();
        triggers.add(cronTrigger);
        triggers.add(simpleTrigger);
        scheduler.scheduleJob(jobDetail, triggers, false);
    }

}
