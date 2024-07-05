package com.quartz;

import lombok.Data;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Data
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String myName = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("myName");
        System.out.println("Hello jod exec："+myName);
//        this.myName = "cc测试";

    }
}
