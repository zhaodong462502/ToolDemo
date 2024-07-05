package com.quartz;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * 功能描述: 定时器配置
 *
 * @author XiaoNianXin
 * @date 2021/12/13 21:08
 */
public class HelloSchedulerDemo {
    public static void main(String[] args) throws SchedulerException {
        // 1、调度器 - 从工厂获取调度实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2、任务实例 - 执行的任务对象
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1") // 任务名称,组名称
                .build();

        job.getJobDataMap().put("myName", "XiaoNianXin");

        // 3、触发器 - 控制执行次数和执行时间
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") // 同上
                .startNow() // 立刻启动
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(2).
                        withRepeatCount(10))
                //.endAt(DateBuilder.nextGivenSecondDate(new Date(), 10))// 循环10次,每次间隔3s
                .build();

        // 调度器关联触发器,并启动
        scheduler.scheduleJob(job,trigger);
        scheduler.start();


//        System.out.println(job.getJobDataMap().get("myName"));

    }
}
