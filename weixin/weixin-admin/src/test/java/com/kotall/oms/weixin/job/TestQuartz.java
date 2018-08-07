package com.kotall.oms.weixin.job;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import java.util.Date;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/2/13
 */
public class TestQuartz {

    public static void main(String[] args) throws Exception {
        JobDetailImpl jobDetail = new JobDetailImpl(); jobDetail.setGroup("test-group"); jobDetail.setName("test-name");
        jobDetail.setJobClass(RefreshAccessTokenJob.class);

        SimpleTriggerImpl trigger = new SimpleTriggerImpl();
        trigger.setName("test-trigger-name");
        trigger.setGroup("test-trigger-group");
        trigger.setStartTime(new Date());
        trigger.setRepeatCount(5);
        trigger.setRepeatInterval(1000*5);

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();
    }
}
