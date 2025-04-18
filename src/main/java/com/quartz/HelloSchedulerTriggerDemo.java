package com.quartz;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * 功能描述: 定时器配置2
 *
 * @author XiaoNianXin
 * @date 2021/12/13 21:08
 */
public class HelloSchedulerTriggerDemo {
    public static void main(String[] args) throws SchedulerException {

        // 任务开始时间推迟 3 s,结束时间推迟 10 s
        Date startData = new Date();
        startData.setTime(startData.getTime() + 1000);
        Date endData = new Date();
        endData.setTime(endData.getTime() + 9000);

        // 1、调度器 - 从工厂获取调度实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2、任务实例 - 执行的任务对象
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1") // 任务名称,组名称
                .usingJobData("msg","JDM使用 - Detail")    // JDM 传递参数
                .build();

        // 3、触发器 - 控制执行次数和执行时间
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") // 同上
                .startNow() // 立刻启动
                .startAt(startData)
                .endAt(endData)
                .build();

        // 调度器关联触发器,并启动
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
    }
}
