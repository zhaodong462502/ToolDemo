package com.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class ScheduleDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String checkStr = "";
        int timeout = 1000;
        int period =250;
        int num =timeout/period;
        CountDownLatch countDownLatch = new CountDownLatch(num);

        long start = System.currentTimeMillis();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        int cnt = 0;
        Runnable runnable = () -> {
           if(cnt > 2){

           }
        };
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(runnable, 0,period, TimeUnit.MILLISECONDS);

        countDownLatch.await();
        scheduledExecutorService.shutdown();
//        scheduledExecutorService.shutdown();
        boolean result = checkRedis();
        if(result){


        }


    }

    public static boolean checkRedis(){
        int i = 0;
        int cnt = 0;
        do {
            //获取缓存数据
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(i == 1){
                return true;
            }
            cnt++;

        }while (cnt<100);

        return false;
    }
}
